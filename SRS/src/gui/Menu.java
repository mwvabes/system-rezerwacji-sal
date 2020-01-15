package gui;

import entities.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import setUp.SetUpConf;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class Menu implements Initializable {

  @FXML
  Text lastActionInfo;
  @FXML
  TableView<TableRoomsEntity> tableRooms;
  @FXML
  private ComboBox<BuildingsEntity> chooseBuildingCombo;
  @FXML
  private ComboBox<RoomTypesEntity> chooseTypeCombo;
  @FXML
  private Text buildingNameInfo;
  @FXML
  private Text buildingAddressInfo;
  @FXML
  private Text buildingAddressInfoLabel;
  @FXML
  private Text buildingAreaInfo;
  @FXML
  private Text buildingAreaInfoLabel;
  @FXML
  private Text buildingFullName;

  @FXML
  private Text floorInfo;
  @FXML
  private Text floorInfoLabel;

  @FXML
  private Text wingInfo;
  @FXML
  private Text wingInfoLabel;

  @FXML
  private Text roomFullName;
  @FXML
  private Text roomFullNameLabel;
  @FXML
  private Text roomNameInfo;
  @FXML
  private Text seatsInfo;
  @FXML
  private Text roomTypeInfo;

  @FXML
  private TextField timeStart;
  @FXML
  private TextField timeEnd;
  @FXML
  private DatePicker dateStart;
  @FXML
  private DatePicker dateEnd;

  @FXML
  private CheckBox showOnlyToReservation;
  @FXML
  private CheckBox takeDatesToQuery;

  @FXML
  private TextArea equipmentDescriptionInfo;

  @FXML
  private TableView<EquipmentInfoEntity> equipmentInfo;

  @FXML
  private Button bookIt;
  @FXML
  private Button statusScreen;
  @FXML
  private Button showReservationHistory;
  @FXML
  private Button myReservations;
  @FXML
  private Button dataManagement;

  private UsersEntity currentLogged = new UsersEntity();
  Session session;
  SetUpConf setUpConf;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setUpConf = new SetUpConf();
    session = setUpConf.setUpStart();


    setUpComboBoxes();
    setUpDates();
    getRoomInfo();

    tableRooms.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      aboutRoomInfo();
    });

    equipmentInfo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      getEquipmentDescription();
    });

    dateStart.valueProperty().addListener((ov, oldValue, newValue) -> {
      disableTakeDatesToQuery();
    });

    dateEnd.valueProperty().addListener((ov, oldValue, newValue) -> {
      disableTakeDatesToQuery();
    });

    timeStart.textProperty().addListener((ov, oldValue, newValue) -> {
      disableTakeDatesToQuery();
    });

    timeEnd.textProperty().addListener((ov, oldValue, newValue) -> {
      disableTakeDatesToQuery();
    });

    takeDatesToQuery.selectedProperty().addListener((ov, oldValue, newValue) -> {
      timeValidation();
    });


  }

  public void setUpDates() {
    LocalDate now = LocalDate.now();

    dateStart.setValue(now);
    dateEnd.setValue(now);

  }

  public void setUserSession(UsersEntity user) {
    currentLogged = user;
    lastActionInfo.setText("Witaj " + user.getName() + " w Systemie Rezerwacji Sal!");

    if (user.getRole() == 1) {
      dataManagement.setVisible(true);
    } else {
      dataManagement.setVisible(false);
    }
  }

  @FXML
  protected void showLogowanie() {
    srs.SceneManager.renderScene("logowanie");
    setUpConf.setUpClose();
  }

  public void getRoomInfo() {
    Transaction transaction = session.beginTransaction();
    String query = " from TableRoomsEntity WHERE idRoom > 0";

    if (chooseTypeCombo.getSelectionModel().getSelectedIndex() > 0 && chooseBuildingCombo.getSelectionModel().getSelectedIndex() == 0) {
      query += "AND idType = " + chooseTypeCombo.getSelectionModel().getSelectedItem().getIdType();
    } else if (chooseTypeCombo.getSelectionModel().getSelectedIndex() == 0 && chooseBuildingCombo.getSelectionModel().getSelectedIndex() > 0) {
      query += "AND idBuilding = " + chooseBuildingCombo.getSelectionModel().getSelectedItem().getIdBuilding();
    } else if (chooseTypeCombo.getSelectionModel().getSelectedIndex() > 0 && chooseBuildingCombo.getSelectionModel().getSelectedIndex() > 0) {
      query += "AND idType = " + chooseTypeCombo.getSelectionModel().getSelectedItem().getIdType() + " AND idBuilding = " + chooseBuildingCombo.getSelectionModel().getSelectedItem().getIdBuilding();
    }


    if (takeDatesToQuery.isSelected()) {
      Timestamp tsStart = Timestamp.valueOf(dateStart.getValue() + " " + timeStart.getText() + ":00");
      Timestamp tsEnd = Timestamp.valueOf(dateEnd.getValue() + " " + timeEnd.getText() + ":00");

      query += " AND idRoom NOT IN ( select idRoom from ReservationsViewEntity where " +
              "(meetTimeStart > '" + tsStart + "' and meetTimeStart < '" + tsEnd + "') or " +
              "(meet_time_end > '" + tsStart + "' and meetTimeEnd < '" + tsEnd + "') or " +
              "(meetTimeStart < '" + tsStart + "' and meetTimeEnd > '" + tsEnd + "') or " +
              "(meetTimeStart = '" + tsStart + "' or meetTimeEnd = '" + tsEnd + "'))";
    }


    if (showOnlyToReservation.isSelected()) {
      query += " AND reservationAbility = 1 ";
    }

    query += " ORDER BY idBuilding ASC";


    List<TableRoomsEntity> rooms = session.createQuery(query).list();


    transaction.commit();

    ObservableList<TableRoomsEntity> roomsEntities = tableRooms.getItems();
    roomsEntities.clear();
    roomsEntities.addAll(rooms);
    if (tableRooms.getItems().size() > 0) {
      tableRooms.getSelectionModel().select(0);
    }
    aboutRoomInfo();


  }

  public void setUpComboBoxes() {
    ObservableList<BuildingsEntity> chooseBuilding = chooseBuildingCombo.getItems();
    ObservableList<RoomTypesEntity> chooseType = chooseTypeCombo.getItems();
    chooseBuilding.add(new BuildingsEntity("Wszystkie budynki"));
    chooseType.add(new RoomTypesEntity("Wszystkie typy"));

    Transaction transaction = session.beginTransaction();
    List<BuildingsEntity> buildings = session.createQuery("from BuildingsEntity ").list();
    List<RoomTypesEntity> types = session.createQuery("from RoomTypesEntity ").list();
    chooseBuilding.addAll(buildings);
    chooseType.addAll(types);
    transaction.commit();

    chooseBuildingCombo.getSelectionModel().select(0);
    chooseTypeCombo.getSelectionModel().select(0);
  }

  public void getEquipmentDescription() {
    if (equipmentInfo.getSelectionModel().getSelectedItem() != null) {
      EquipmentInfoEntity selected = equipmentInfo.getSelectionModel().getSelectedItem();
      equipmentDescriptionInfo.setText(selected.getDescription());
    } else {
      equipmentDescriptionInfo.setText(" ");
    }
  }

  public void aboutRoomInfo() {
    ObservableList<TableRoomsEntity> rooms = tableRooms.getItems();

    if (tableRooms.getSelectionModel().getSelectedItem() != null) {
      TableRoomsEntity room = tableRooms.getSelectionModel().getSelectedItem();
      buildingNameInfo.setText(room.getBuildingName());
      roomNameInfo.setText(room.getRoomName());
      seatsInfo.setText(String.valueOf(room.getSeats()));
      if (!room.getFloor().equals("-")) {
        floorInfo.setText(room.getFloor());
        floorInfoLabel.setText("Piętro");
      } else {
        floorInfo.setText(" ");
        floorInfoLabel.setText("--");
      }
      if (!room.getWing().equals("-")) {
        wingInfo.setText(room.getWing());
        wingInfoLabel.setText("Skrzydło");
      } else {
        wingInfo.setText(" ");
        wingInfoLabel.setText("--");
      }
      if (!room.getRoomFullName().equals("-")) {
        roomFullName.setText(room.getRoomFullName());
        roomFullNameLabel.setText("Pełna nazwa pomieszczenia");
      } else {
        roomFullName.setText(" ");
        roomFullNameLabel.setText("--");
      }
      buildingAddressInfo.setText(room.getAddress());
      buildingAddressInfoLabel.setText("Adres budynku");
      buildingAreaInfo.setText(room.getArea());
      buildingAreaInfoLabel.setText("Rejon");
      roomTypeInfo.setText(room.getTypeName());
      if (!room.getBuildingFullName().equals("-")) {
        buildingFullName.setText(room.getBuildingFullName());
      } else {
        buildingFullName.setText(" ");
      }
      statusScreen.setVisible(true);
      if (room.getReservationAbility() != 0) {
        showReservationHistory.setDisable(false);
      } else showReservationHistory.setDisable(true);

      ObservableList<EquipmentInfoEntity> equipmentList = equipmentInfo.getItems();
      Transaction transaction = session.beginTransaction();
      List<EquipmentInfoEntity> equipments = session.createQuery("from EquipmentInfoEntity WHERE idRoom = " + room.getIdRoom()).list();
      equipmentList.clear();
      equipmentList.addAll(equipments);
      transaction.commit();

      if (takeDatesToQuery.isSelected() && room.getReservationAbility() == 1) {
        bookIt.setDisable(false);
      } else {
        bookIt.setDisable(true);
      }


    } else {
      buildingNameInfo.setText("--");
      roomNameInfo.setText("-");
      seatsInfo.setText(("--"));
      floorInfo.setText(" ");
      floorInfoLabel.setText("--");
      wingInfo.setText(" ");
      wingInfoLabel.setText("--");
      roomFullName.setText(" ");
      roomFullNameLabel.setText("--");
      buildingAddressInfo.setText(" ");
      buildingAddressInfoLabel.setText("--");
      buildingAreaInfo.setText(" ");
      buildingAreaInfoLabel.setText("--");
      roomTypeInfo.setText(" ");
      buildingFullName.setText(" ");
      statusScreen.setVisible(false);
      bookIt.setDisable(false);
    }

  }

  public String translateTimestamp(Timestamp ts) {
    String date = String.valueOf(ts.toLocalDateTime().minusHours(1)).replaceAll("T", " ");
    String newDate = date.substring(8, 10) + "-" + date.substring(5, 7) + "-" + date.substring(0, 4) + " " + date.substring(11, 16);
    return newDate;
  }

  @FXML
  public void bookIt() {
    Timestamp tsStart = Timestamp.valueOf(Timestamp.valueOf(dateStart.getValue() + " " + timeStart.getText() + ":00").toLocalDateTime().plusHours(1));
    Timestamp tsEnd = Timestamp.valueOf(Timestamp.valueOf(dateEnd.getValue() + " " + timeEnd.getText() + ":00").toLocalDateTime().plusHours(1));

    TableRoomsEntity selectedRoom = tableRooms.getSelectionModel().getSelectedItem();

    ButtonType buttonTypeYes = new ButtonType("Potwierdzam");
    ButtonType buttonTypeCancel = new ButtonType("ANULUJ");

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Zamierzasz wykonać rezerwację sali " + selectedRoom.getRoomName() + " od " + translateTimestamp(tsStart) + " do " + translateTimestamp(tsEnd), buttonTypeYes, buttonTypeCancel);
    alert.setTitle("Potwierdzenie wykonania rezerwacji");
    alert.setHeaderText("Czy na pewno?");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();

    if (alert.getResult() == buttonTypeYes) {
//      Transaction transaction = session.beginTransaction();
//      Query q = session.createQuery("delete ReservationsEntity where idReservation = " + selectedReservation.getIdReservation());
//      q.executeUpdate();
//      transaction.commit();
      ReservationsEntity reservation = new ReservationsEntity();
      reservation.setIdRoom(selectedRoom.getIdRoom());
      reservation.setIdUser(currentLogged.getIdUser());
      reservation.setMeetTimeStart(tsStart);
      reservation.setMeetTimeEnd(tsEnd);
      Transaction transaction = session.beginTransaction();
      session.save(reservation);
      transaction.commit();
      lastActionInfo.setText("Zarezerwowano salę!");
      takeDatesToQuery.setSelected(false);
      getRoomInfo();

    } else {
      //...
    }
  }

  public void openStatusScreen() throws IOException {
    if (tableRooms.getSelectionModel().getSelectedItem() != null) {
      TableRoomsEntity selectedRoom = tableRooms.getSelectionModel().getSelectedItem();
      String path = "";
      FXMLLoader fxmlLoader = new FXMLLoader();

      fxmlLoader.setLocation(getClass().getResource("/gui/StatusScreen.fxml"));
      /*
       * if "fx:controller" is not set in fxml
       * fxmlLoader.setController(NewWindowController);
       */
      Scene scene1 = new Scene(fxmlLoader.load(), 600, 400);
      Stage stage1 = new Stage();
      stage1.setTitle("Status sali " + selectedRoom.getRoomName() + " | " + selectedRoom.getBuildingName());
      stage1.setScene(scene1);
      stage1.show();
      StatusScreen statusScreen = fxmlLoader.getController();

      Transaction transaction = session.beginTransaction();
      List<ReservationsViewEntity> reservations = session.createQuery("from ReservationsViewEntity WHERE idRoom = " + selectedRoom.getIdRoom()).list();
      transaction.commit();

      statusScreen.getInformation(selectedRoom, reservations);
    }
  }

  @FXML
  public void myReservations() throws IOException {
    String path = "";
    FXMLLoader fxmlLoaderMyres = new FXMLLoader();

    fxmlLoaderMyres.setLocation(getClass().getResource("/gui/MyReservations.fxml"));
    /*
     * if "fx:controller" is not set in fxml
     * fxmlLoader.setController(NewWindowController);
     */
    Scene scene1 = new Scene(fxmlLoaderMyres.load(), 600, 400);
    Stage stage1 = new Stage();
    stage1.setTitle("Moje rezerwacje");
    stage1.setScene(scene1);
    stage1.show();
    MyReservations myReservationsController = fxmlLoaderMyres.getController();

    myReservationsController.setUpUserLogged(currentLogged);
    myReservationsController.setUp();

  }

  public void timeValidation() {
    TextField[] fields = {timeEnd, timeStart};
    for (int i = 0; i < fields.length; i++) {
      if (fields[i].textProperty().get().matches("^\\d:\\d\\d$")) {

        fields[i].textProperty().setValue("0" + fields[i].textProperty().get());

      } else if (fields[i].textProperty().get().matches("^\\d\\d:\\d\\d$")) {
      } else {
        fields[i].textProperty().setValue("00:00");
      }
    }

    LocalDateTime now = LocalDateTime.now();
    if (dateStart.getValue().isAfter(dateEnd.getValue())) {
      dateStart.setValue(LocalDate.from(now));
      dateEnd.setValue(LocalDate.from(now));
    }

    Timestamp tsStart = Timestamp.valueOf(dateStart.getValue() + " " + timeStart.getText() + ":00");
    Timestamp tsEnd = Timestamp.valueOf(dateEnd.getValue() + " " + timeEnd.getText() + ":00");

    if (tsStart.after(tsEnd)) {
      dateStart.setValue(LocalDate.from(now));
      dateEnd.setValue(LocalDate.from(now));
      timeStart.setText("08:00");
      timeEnd.setText("16:00");
    }

    if (tsEnd.toLocalDateTime().isBefore(now)) {
      dateEnd.setValue(now.toLocalDate().plusDays(1));
      lastActionInfo.setText("Brak możliwości rezerwacji w przeszłości!");
    }

//    reservationInfo.setTimeStart(Timestamp.valueOf(start));
//    reservationInfo.setTimeEnd(Timestamp.valueOf(end));
//    if (!reservationInfo.isTimeSlotValid()) {
//      lastActionInfo.setText("Nieprawidłowy przedział czasowy!");
//      isDateValid = false;
//    } else {
//      lastActionInfo.setText("Wyświetlam sale dostępne w podanym przedziale czasu");
//      isDateValid = true;
//    }

    getRoomInfo();

  }

  @FXML
  public void showReservationHistory() {
    Transaction transaction = session.beginTransaction();
    TableRoomsEntity selectedRoom = tableRooms.getSelectionModel().getSelectedItem();
    List<ReservationsViewEntity> reservations = session.createQuery("from ReservationsViewEntity WHERE idRoom = " + selectedRoom.getIdRoom() + " ORDER BY meetTimeStart DESC").list();
    transaction.commit();

    String path = "";
    FXMLLoader fxmlLoader = new FXMLLoader();
    try {

      fxmlLoader.setLocation(getClass().getResource("/gui/ReservationHistory.fxml"));
      /*
       * if "fx:controller" is not set in fxml
       * fxmlLoader.setController(NewWindowController);
       */
      Scene scene1 = new Scene(fxmlLoader.load(), 600, 400);
      Stage stage1 = new Stage();
      stage1.setTitle("Historia rezerwacji sali " + selectedRoom.getRoomName() + " | " + selectedRoom.getBuildingName());
      stage1.setScene(scene1);
      stage1.show();

    } catch (IOException e) {
      System.err.println("Nie można załadować pliku XML z widokiem: " + path);
      e.printStackTrace();
    } catch (RuntimeException e) {
      System.err.println("Nazwa widoku jest nieprawidłowa!");
      System.out.println(e);
      //e.printStackTrace();
    }

    ReservationHistory reservation = fxmlLoader.getController();

    reservation.setUp(reservations, selectedRoom);

  }

  public void dataManagement(ActionEvent actionEvent) throws IOException {
    srs.SceneManager.renderManagementPanel(currentLogged);
  }

  public void disableTakeDatesToQuery() {
    takeDatesToQuery.setSelected(false);
  }

}
