package gui;

import entities.ReservationsViewEntity;
import entities.TableRoomsEntity;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class StatusScreen implements Initializable {

  @FXML
  private Text roomTypeInfo;
  @FXML
  private Text currentTimeInfo;
  @FXML
  private Text roomNameInfo;
  @FXML
  private Text roomStatusInfo;
  @FXML
  private Text roomFullName;
  @FXML
  private Text nextMeetingLabel;
  @FXML
  private Text nextMeetingTime;
  @FXML
  private Text timerRemaining;


  @Override
  public void initialize(URL location, ResourceBundle resources) {

    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
    Date date = new Date();
    currentTimeInfo.setText(formatter.format(date));
  }

  public String translateTimestamp(Timestamp ts) {
    String date = String.valueOf(ts.toLocalDateTime().minusHours(1)).replaceAll("T", " ");
    String newDate = date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4) + " " + date.substring(11, 16);
    return newDate;
  }

  public void getInformation(TableRoomsEntity room, List<ReservationsViewEntity> reservations) {
    Date dateT = new Date();
    roomTypeInfo.setText(room.getTypeName());
    roomNameInfo.setText(room.getRoomName());
    if (!room.getRoomFullName().equals("-")) {
      roomFullName.setText(room.getRoomFullName());
    }

    Timestamp now = new Timestamp(dateT.getTime());

    boolean isClosest = false;

    List<ReservationsViewEntity> reservationsAfterNow = new ArrayList<ReservationsViewEntity>();
    ReservationsViewEntity nowReservation = new ReservationsViewEntity();
    boolean isReservationNow = false;

    for (ReservationsViewEntity r : reservations) {
      if (r.getMeetTimeStart().before(now) && r.getMeetTimeEnd().after(now)) {
        nowReservation = r;
        isReservationNow = true;
      }
      if (r.getMeetTimeStart().after(now)) reservationsAfterNow.add(r);
    }

    if (reservationsAfterNow.size() == 0) nextMeetingLabel.setText("Brak rezerwacji w przyszłości");
    else {
      isClosest = true;
      ReservationsViewEntity closestMeeting = Collections.max(reservationsAfterNow, Comparator.comparing(r -> r.getMeetTimeStart()));
      nextMeetingTime.setText(String.valueOf(translateTimestamp(closestMeeting.getMeetTimeStart())));
      nextMeetingLabel.setText("Najbliższe spotkanie");
    }

    if (room.getReservationAbility() == 0) {
      roomStatusInfo.setText("~");
      nextMeetingLabel.setText(" * * * * *");
    } else if (isReservationNow) {
      roomStatusInfo.setText("Trwa spotkanie");

      ReservationsViewEntity finalNowReservation = nowReservation;
      System.out.println(finalNowReservation.getMeetTimeEnd());
      System.out.println(finalNowReservation.getMeetTimeEnd().toLocalDateTime());
      System.out.println(finalNowReservation.getMeetTimeEnd().getTime());
      AnimationTimer timer1 = new AnimationTimer() {
        private long lastUpdate = 0;

        @Override
        public void handle(long now) {
          if (now > lastUpdate + 1_000_000_000l) {
            final int SECONDS_PER_DAY = 86_400;
            final int SECONDS_PER_HOUR = 3600;
            final int SECONDS_PER_MINUTE = 60;
            if (LocalDateTime.now().isBefore(finalNowReservation.getMeetTimeEnd().toLocalDateTime())) {
              //duration = duration.subtract(Duration.seconds(1));

              Instant nowNew = finalNowReservation.getMeetTimeEnd().toInstant();
              Instant toNew = new Timestamp(System.currentTimeMillis()).toInstant();

              Duration duration = Duration.between(nowNew, toNew);
              int nowMilis = (int) new Timestamp(System.currentTimeMillis()).getTime();
              //int remainingSeconds = (int) (finalNowReservation.getMeetTimeEnd().getTime() - nowMilis);
              int remainingSeconds = (int) duration.getSeconds();

              int d = -remainingSeconds / SECONDS_PER_DAY;
              int h = (-remainingSeconds % SECONDS_PER_DAY) / SECONDS_PER_HOUR;
              int m = ((-remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE;
              int s = (((-remainingSeconds % SECONDS_PER_DAY) % SECONDS_PER_HOUR) % SECONDS_PER_MINUTE);

//              if (d == 0 && h == 0 && m == 0 && s == 0) {
//                timer.stop();
//              }

              timerRemaining.setText("Do końca spotkania pozostało " + h + "h " + m + "m " + s + "s");

            } else {
              timerRemaining.setText("");
            }
            lastUpdate = now;
          }
        }
      };
      timer1.start();
    } else {
      roomStatusInfo.setText("Sala dostępna");
    }

  }

}
