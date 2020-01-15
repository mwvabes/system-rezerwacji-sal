package gui;

import entities.ReservationsViewEntity;
import entities.UsersEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import setUp.SetUpConf;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class MyReservations implements Initializable {

  @FXML
  Button closeButton;
  @FXML
  Button cancelReservation;

  @FXML
  TableView<ReservationsViewEntity> reservationsInfoTable;

  private UsersEntity currentLogged = new UsersEntity();
  Session session;
  SetUpConf setUpConf;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setUpConf = new SetUpConf();
    session = setUpConf.setUpStart();

    reservationsInfoTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      isValidForDelete();
    });
  }

  public void setUpUserLogged(UsersEntity user) {
    currentLogged = user;
  }

  @FXML
  public void setUp() {
    Transaction transaction = session.beginTransaction();
    List<ReservationsViewEntity> myReservations = session.createQuery("from ReservationsViewEntity WHERE idUser = " + currentLogged.getIdUser() + " ORDER BY meetTimeStart DESC").list();
    transaction.commit();
    ObservableList<ReservationsViewEntity> table = reservationsInfoTable.getItems();

    for (ReservationsViewEntity r : myReservations) {
      r.setMeetTimeStart(Timestamp.valueOf(r.getMeetTimeStart().toLocalDateTime().minusHours(1)));
      r.setMeetTimeEnd(Timestamp.valueOf(r.getMeetTimeEnd().toLocalDateTime().minusHours(1)));
    }

    table.clear();
    table.addAll(myReservations);
  }

  @FXML
  public void closeStage(ActionEvent actionEvent) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  public void isValidForDelete() {
    if (reservationsInfoTable.getSelectionModel().getSelectedItem() != null && reservationsInfoTable.getSelectionModel().getSelectedItem().getMeetTimeStart().toLocalDateTime().isAfter(LocalDateTime.now())) {
      cancelReservation.setDisable(false);
    } else {
      cancelReservation.setDisable(true);
    }
  }

  public String translateTimestamp(Timestamp ts) {
    String date = String.valueOf(ts.toLocalDateTime().minusHours(1)).replaceAll("T", " ");
    String newDate = date.substring(8,10) + "-" + date.substring(5,7) + "-" + date.substring(0,4) + " " + date.substring(11,16);
    return newDate;
  }

  public void deleteReservation(ActionEvent actionEvent) {

    ReservationsViewEntity selectedReservation = reservationsInfoTable.getSelectionModel().getSelectedItem();

    ButtonType buttonTypeYes = new ButtonType("Potwierdzam");
    ButtonType buttonTypeCancel = new ButtonType("ANULUJ");

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Rezerwacja sali " + selectedReservation.getRoomName() + " od " + translateTimestamp(selectedReservation.getMeetTimeStart()) + " do " + translateTimestamp(selectedReservation.getMeetTimeEnd()), buttonTypeYes, buttonTypeCancel);
    alert.setTitle("Potwierdzenie usunięcia");
    alert.setHeaderText("Czy na pewno?");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();

    if (alert.getResult() == buttonTypeYes) {
      Transaction transaction = session.beginTransaction();
      session.clear();
      Query q = session.createQuery("delete ReservationsEntity where idReservation = " + selectedReservation.getIdReservation());
      q.executeUpdate();
      transaction.commit();
      System.out.println("Usunięto");
      setUp();
    } else {
      System.out.println("Anulowano");
    }

  }
}
