package srs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import srs.subtypes.ReservationInfo;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class StatusScreen implements Initializable {

  @FXML private Text roomTypeInfo;
  @FXML private Text currentTimeInfo;
  @FXML private Text roomNameInfo;
  @FXML private Text roomStatusInfo;
  @FXML private Text roomFullName;
  @FXML private Text nextMeetingLabel;
  @FXML private Text nextMeetingTime;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void loadData(int roomId) throws SQLException {
    Connection conn = new DbConnection().connect();

    Date dateT = new Date();
    Timestamp now = new Timestamp(dateT.getTime());

    Statement stmt = null;
    Statement stmtIsRoomAvailable = null;
    Statement stmtNearestMeeting = null;
    String query = "select * from srs.rooms, srs.room_types WHERE rooms.ID_type = room_types.id_type AND rooms.ID_room = " + roomId;
    String queryIsRoomAvailable = "select * from reservations WHERE ID_room = " + roomId + " AND '" + now + "' BETWEEN meet_time_start AND meet_time_end";
    String queryNearestMeeting = "select * from reservations WHERE ID_room = " + roomId + " AND '" + now + "' < meet_time_start ORDER BY meet_time_start LIMIT 1 ";
    System.out.println(queryNearestMeeting);
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      stmtIsRoomAvailable = conn.createStatement();
      ResultSet rsIsRoomAvailable = stmtIsRoomAvailable.executeQuery(queryIsRoomAvailable);
      stmtNearestMeeting = conn.createStatement();
      ResultSet rsNearestMeeting = stmtNearestMeeting.executeQuery(queryNearestMeeting);

      while (rs.next()) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();



        roomTypeInfo.setText(rs.getString("room_types.name"));
        currentTimeInfo.setText(formatter.format(date));
        roomNameInfo.setText(rs.getString("rooms.name"));
        if (!rs.getString("rooms.fullName").equals("-")) {
          roomFullName.setText(rs.getString("rooms.fullName"));
        } else {
          roomFullName.setText(" ");
        }


        if (rsIsRoomAvailable.next()) {
          roomStatusInfo.setText("Trwa spotkanie");
        } else {
          roomStatusInfo.setText("Sala dostępna");
        }

        if (rsNearestMeeting.next()) {

          Timestamp nearestMeetinTimestamp = rsNearestMeeting.getTimestamp("reservations.meet_time_start");
          SimpleDateFormat formatterE = new SimpleDateFormat("dd-MM-YYYY HH:mm");
          Date dateE = nearestMeetinTimestamp;

          //nextMeetingTime.setText(String.valueOf(rsNearestMeeting.getTimestamp("reservations.meet_time_start")));
          nextMeetingTime.setText(formatterE.format(dateE));
          nextMeetingLabel.setText("Najbliższe spotkanie");
        } else {
          nextMeetingTime.setText("Brak zaplanowanych spotkań");
          nextMeetingLabel.setText(" ");
        }

      }


    } catch (SQLException e) {
      System.out.println(e);
    } finally {
      if (stmt != null) {
        stmt.close();
      }
    }
  }

}
