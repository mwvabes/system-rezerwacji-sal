package gui;

import entities.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import setUp.SetUpConf;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManagementPanel implements Initializable {

  @FXML
  ComboBox<RoomsEntity> eqAllocComboRoom;
  @FXML
  ComboBox<EquipmentEntity> equipmentComboEq;
  @FXML
  Button deleteEqAlloc;
  @FXML
  TextField eqAmountInput;

  @FXML
  TableView<RoomTypesEntity> roomTypesTable;
  @FXML
  TableView<UsersEntity> usersTable;
  @FXML
  TableView<BuildingsEntity> buildingsTable;
  @FXML
  TableView<EquipmentEntity> equipmentTable;
  @FXML
  TableView<EquipmentInfoEntity> equipmentAllocTable;
  @FXML
  TextField equipmentName;
  @FXML
  TextField equipmentDescription;
  @FXML
  Button equipmentAddNew;
  @FXML
  Button equipmentDelete;
  @FXML
  Button equipmentSave;
  @FXML
  Button addNewBuilding;
  @FXML
  Button deleteBuilding;
  @FXML
  Button buildingSave;
  @FXML
  Button userAddNew;
  @FXML
  Button userDelete;
  @FXML
  Button userSave;
  @FXML
  TextField userName;
  @FXML
  TextField userSurname;
  @FXML
  TextField userDescription;
  @FXML
  CheckBox userIsAdmin;
  @FXML
  TextField userUsername;
  @FXML
  PasswordField userPassword;

  @FXML
  TableView<TableRoomsEntity> roomsTable;

  @FXML
  Button roomAddNew;
  @FXML
  Button roomDelete;
  @FXML
  Button roomSave;
  @FXML
  ComboBox<BuildingsEntity> roomBuildingInput;
  @FXML
  TextField roomNameInput;
  @FXML
  TextField roomFullNameInput;
  @FXML
  TextField roomSeatsInput;
  @FXML
  TextField roomFloorInput;
  @FXML
  TextField roomWingInput;
  @FXML
  CheckBox roomResAbilityInput;
  @FXML
  ComboBox<RoomTypesEntity> roomTypeInput;

  @FXML
  Button addNewRoomType;
  @FXML
  Button deleteRoomType;
  @FXML
  Button saveRoomType;
  @FXML
  TextField roomTypeNameInput;

  @FXML
  TextField buildingName;
  @FXML
  TextField buildingFullName;
  @FXML
  TextField buildingTown;
  @FXML
  TextField buildingAddress;
  @FXML
  TextField buildingArea;


  private UsersEntity currentLogged = new UsersEntity();

  Session session;
  SetUpConf setUpConf;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setUpConf = new SetUpConf();
    session = setUpConf.setUpStart();

    buildingsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      initBuildingsInfo();
    });

    roomsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      initRoomInfo();
    });

    roomTypesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      initRoomTypesInfo();
    });

    usersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      initUsersInfo();
    });

    equipmentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      initEquipmentInfo();
    });

    eqAllocComboRoom.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      initEquipmentAllocInfo();
    });

    equipmentAllocTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      initEquipmentAllocSpecInfo();
    });

    initBuildingsTable();
    initRoomTypesTable();
    initRoomsTable();
    initUsersTable();
    initEquipmentTable();
    initEquipmentAlloc();
  }

  public void setUserSession(UsersEntity user) {
    currentLogged = user;
  }

  public void initRoomsTable() {
    Transaction transaction = session.beginTransaction();
    List<TableRoomsEntity> rooms = session.createQuery("from TableRoomsEntity ").list();
    transaction.commit();

    ObservableList<TableRoomsEntity> roomsEntities = roomsTable.getItems();
    roomsEntities.clear();
    roomsEntities.addAll(rooms);
  }

  public void initRoomInfo() {

    if (roomsTable.getSelectionModel().getSelectedItem() != null) {
      ObservableList<TableRoomsEntity> roomsEntities = roomsTable.getItems();
      TableRoomsEntity selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
      roomDelete.setDisable(false);

      Transaction transaction = session.beginTransaction();
      List<BuildingsEntity> buildings = session.createQuery("from BuildingsEntity ").list();
      List<RoomTypesEntity> roomTypes = session.createQuery("from RoomTypesEntity ").list();
      transaction.commit();

      roomBuildingInput.getItems().clear();
      roomBuildingInput.getItems().add(new BuildingsEntity(""));
      roomBuildingInput.getItems().addAll(buildings);
      roomBuildingInput.getSelectionModel().select(selectedRoom.getIdBuilding());
      roomNameInput.setText(selectedRoom.getRoomName());
      roomFullNameInput.setText(selectedRoom.getRoomFullName());
      roomSeatsInput.setText(String.valueOf(selectedRoom.getSeats()));
      roomFloorInput.setText(selectedRoom.getFloor());
      roomWingInput.setText(selectedRoom.getWing());
      if (selectedRoom.getReservationAbility() == 1) {
        roomResAbilityInput.setSelected(true);
      } else roomResAbilityInput.setSelected(false);
      roomTypeInput.getItems().clear();
      roomTypeInput.getItems().add(new RoomTypesEntity(""));
      roomTypeInput.getItems().addAll(roomTypes);
      roomTypeInput.getSelectionModel().select(selectedRoom.getIdType());


    } else {
      roomDelete.setDisable(true);
      roomBuildingInput.getItems().clear();
      roomNameInput.setText("");
      roomFullNameInput.setText("");
      roomSeatsInput.setText("");
      roomFloorInput.setText("");
      roomWingInput.setText("");
      roomResAbilityInput.setSelected(false);
      roomTypeInput.getItems().clear();
    }


  }

  public void initRoomTypesTable() {
    Transaction transaction = session.beginTransaction();
    List<RoomTypesEntity> roomTypes = session.createQuery("from RoomTypesEntity ").list();
    transaction.commit();

    ObservableList<RoomTypesEntity> roomTypesEntities = roomTypesTable.getItems();
    roomTypesEntities.clear();
    roomTypesEntities.addAll(roomTypes);
  }

  public void initRoomTypesInfo() {

    if (roomTypesTable.getSelectionModel().getSelectedItem() != null) {
      ObservableList<RoomTypesEntity> roomTypesEntities = roomTypesTable.getItems();
      RoomTypesEntity selectedRoomType = roomTypesTable.getSelectionModel().getSelectedItem();
      deleteRoomType.setDisable(false);

      roomTypeNameInput.setText(selectedRoomType.getName());
    } else {
      roomTypeNameInput.setText("");
      deleteRoomType.setDisable(true);
    }

  }

  public void addNewRoomType(ActionEvent actionEvent) {
    RoomTypesEntity newRoomType = new RoomTypesEntity();
    newRoomType.setName(roomTypeNameInput.getText());
    Transaction transaction = session.beginTransaction();
    session.clear();
    session.save(newRoomType);
    transaction.commit();
    roomTypeNameInput.setText(" ");
    initRoomTypesTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Dodano nowy typ");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();

  }

  public void saveRoomType(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    Query q = session.createQuery("UPDATE RoomTypesEntity SET name = '" + roomTypeNameInput.getText() + "' WHERE idType  = " + roomTypesTable.getSelectionModel().getSelectedItem().getIdType());
    q.executeUpdate();
    transaction.commit();
    initRoomTypesTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Zapisano zmiany");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void deleteRoomType(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    Query q = session.createQuery("delete RoomTypesEntity WHERE idType = " + roomTypesTable.getSelectionModel().getSelectedItem().getIdType());
    q.executeUpdate();
    transaction.commit();
    initRoomTypesTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.WARNING, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Usunięto typ");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void closeStage(ActionEvent actionEvent) throws IOException {
    srs.SceneManager.renderMenuUserLogged(currentLogged);
  }

  public void addNewRoom(ActionEvent actionEvent) {
    RoomsEntity newRoom = new RoomsEntity();
    newRoom.setIdBuilding(roomBuildingInput.getSelectionModel().getSelectedItem().getIdBuilding());
    newRoom.setName(roomNameInput.getText());
    newRoom.setFullName(roomFullNameInput.getText());
    newRoom.setSeats(Integer.parseInt(roomSeatsInput.getText()));
    newRoom.setFloor(roomFloorInput.getText());
    newRoom.setWing(roomWingInput.getText());
    if (roomResAbilityInput.isSelected()) {
      newRoom.setReservationAbility((byte) 1);
    } else newRoom.setReservationAbility((byte) 0);
    newRoom.setIdType(roomTypeInput.getSelectionModel().getSelectedItem().getIdType());

    Transaction transaction = session.beginTransaction();
    session.clear();
    session.save(newRoom);
    transaction.commit();
    roomBuildingInput.getItems().clear();
    roomNameInput.setText("");
    roomFullNameInput.setText("");
    roomSeatsInput.setText("");
    roomFloorInput.setText("");
    roomWingInput.setText("");
    roomResAbilityInput.setSelected(false);
    roomTypeInput.getItems().clear();
    initRoomsTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Dodano nową salę");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void saveRoom(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    RoomsEntity newRoom = new RoomsEntity();
    newRoom.setIdBuilding(roomBuildingInput.getSelectionModel().getSelectedItem().getIdBuilding());
    newRoom.setName(roomNameInput.getText());
    newRoom.setFullName(roomFullNameInput.getText());
    newRoom.setSeats(Integer.parseInt(roomSeatsInput.getText()));
    newRoom.setFloor(roomFloorInput.getText());
    newRoom.setWing(roomWingInput.getText());
    if (roomResAbilityInput.isSelected()) {
      newRoom.setReservationAbility((byte) 1);
    } else newRoom.setReservationAbility((byte) 0);
    newRoom.setIdType(roomTypeInput.getSelectionModel().getSelectedItem().getIdType());

    Query q = session.createQuery("UPDATE RoomsEntity SET name = '" + newRoom.getName() + "', idBuilding = '" + newRoom.getIdBuilding() + "', fullName = '" + newRoom.getFullName() + "', seats = " + newRoom.getSeats() + ", floor = '" + newRoom.getFloor() + "', wing = '" + newRoom.getWing() + "', reservationAbility = " + newRoom.getReservationAbility() + ", idType = " + newRoom.getIdType() + " WHERE idRoom = " + roomsTable.getSelectionModel().getSelectedItem().getIdRoom());
    q.executeUpdate();
    transaction.commit();
    initRoomsTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Zapisano zmiany");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void deleteRoom(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    Query q = session.createQuery("delete RoomsEntity WHERE idRoom = " + roomsTable.getSelectionModel().getSelectedItem().getIdRoom());
    q.executeUpdate();
    transaction.commit();
    initRoomsTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.WARNING, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Usunięto salę");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void initUsersTable() {
    Transaction transaction = session.beginTransaction();
    List<UsersEntity> users = session.createQuery("from UsersEntity ").list();
    transaction.commit();

    ObservableList<UsersEntity> usersEntities = usersTable.getItems();
    usersEntities.clear();
    usersEntities.addAll(users);
  }

  public void initUsersInfo() {

    if (usersTable.getSelectionModel().getSelectedItem() != null) {
      ObservableList<UsersEntity> usersEntities = usersTable.getItems();
      UsersEntity selectedUser = usersTable.getSelectionModel().getSelectedItem();
      if (selectedUser.getIdUser() == 1) {
        userIsAdmin.setSelected(true);
        userIsAdmin.setDisable(true);
        userDelete.setDisable(true);
      } else if (selectedUser.getRole() == 1) {
        userIsAdmin.setSelected(true);
        userIsAdmin.setDisable(false);
        userDelete.setDisable(false);
      } else {
        userIsAdmin.setSelected(false);
        userIsAdmin.setDisable(false);
        userDelete.setDisable(false);
      }
      userName.setText(selectedUser.getName());
      userSurname.setText(selectedUser.getSurname());
      userDescription.setText(selectedUser.getDescription());
      userUsername.setText(selectedUser.getDescription());
      userPassword.setText("");

    } else {
      userIsAdmin.setSelected(false);
      userName.setText("");
      userSurname.setText("");
      userDescription.setText("");
      userUsername.setText("");
      userPassword.setText("");
      userDelete.setDisable(true);
    }

  }

  public void deleteUser(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    Query q = session.createQuery("delete UsersEntity WHERE idUser = " + usersTable.getSelectionModel().getSelectedItem().getIdUser());
    q.executeUpdate();
    transaction.commit();
    userIsAdmin.setSelected(false);
    userName.setText("");
    userSurname.setText("");
    userDescription.setText("");
    userUsername.setText("");
    userPassword.setText("");
    userDelete.setDisable(true);
    initUsersTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.WARNING, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Usunięto użytkownika");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();

  }

  public void addNewUser(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    UsersEntity newUser = new UsersEntity();
    newUser.setName(userName.getText());
    newUser.setSurname(userSurname.getText());
    newUser.setDescription(userDescription.getText());
    if (userIsAdmin.isSelected()) {
      newUser.setRole(1);
    } else newUser.setRole(0);
    newUser.setUsername(userUsername.getText());
    newUser.setPassword(userPassword.getText());


    session.save(newUser);
    transaction.commit();
    initUsersTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Dodano nowego użytkownika");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void saveUser(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    UsersEntity newUser = new UsersEntity();
    newUser.setName(userName.getText());
    newUser.setSurname(userSurname.getText());
    newUser.setDescription(userDescription.getText());
    if (userIsAdmin.isSelected()) {
      newUser.setRole(1);
    } else newUser.setRole(0);
    newUser.setUsername(userUsername.getText());
    Query q;
    if (userPassword.getText().equals("")) {
      q = session.createQuery("UPDATE UsersEntity SET name = '" + newUser.getName() + "', surname = '" + newUser.getSurname() + "', reservationAbility = 1, description = '" + newUser.getDescription() + "', username = '" + newUser.getUsername() + "' WHERE idUser = " + usersTable.getSelectionModel().getSelectedItem().getIdUser());
    } else {
      newUser.setPassword(userPassword.getText());
      q = session.createQuery("UPDATE UsersEntity SET name = '" + newUser.getName() + "', surname = '" + newUser.getSurname() + "', reservationAbility = 1, description = '" + newUser.getDescription() + "', username = '" + newUser.getUsername() + "', password = '" + newUser.getPassword() + "' WHERE idUser = " + usersTable.getSelectionModel().getSelectedItem().getIdUser());
    }

    q.executeUpdate();
    transaction.commit();
    initRoomsTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Zapisano zmiany");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void initBuildingsTable() {
    Transaction transaction = session.beginTransaction();
    List<BuildingsEntity> users = session.createQuery("from BuildingsEntity ").list();
    transaction.commit();

    ObservableList<BuildingsEntity> buildingsEntities = buildingsTable.getItems();
    buildingsEntities.clear();
    buildingsEntities.addAll(users);
  }

  public void initBuildingsInfo() {

    if (buildingsTable.getSelectionModel().getSelectedItem() != null) {
      ObservableList<BuildingsEntity> buildingsEntities = buildingsTable.getItems();
      BuildingsEntity selectedBuilding = buildingsTable.getSelectionModel().getSelectedItem();
      deleteBuilding.setDisable(false);

      buildingName.setText(selectedBuilding.getName());
      buildingFullName.setText(selectedBuilding.getFullName());
      buildingTown.setText(selectedBuilding.getTown());
      buildingAddress.setText(selectedBuilding.getAddress());
      buildingArea.setText(selectedBuilding.getArea());

    } else {
      buildingName.setText("");
      buildingFullName.setText("");
      buildingTown.setText("");
      buildingAddress.setText("");
      buildingArea.setText("");
      deleteRoomType.setDisable(true);
    }

  }

  public void buildingAdd(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    BuildingsEntity newBuilding = new BuildingsEntity();
    newBuilding.setName(buildingName.getText());
    newBuilding.setFullName(buildingFullName.getText());
    newBuilding.setTown(buildingTown.getText());
    newBuilding.setAddress(buildingAddress.getText());
    newBuilding.setArea(buildingArea.getText());

    session.save(newBuilding);
    transaction.commit();
    initBuildingsTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Dodano nowy budynek");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void buildingSave(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    BuildingsEntity newBuilding = new BuildingsEntity();
    newBuilding.setName(buildingName.getText());
    newBuilding.setFullName(buildingFullName.getText());
    newBuilding.setTown(buildingTown.getText());
    newBuilding.setAddress(buildingAddress.getText());
    newBuilding.setArea(buildingArea.getText());

    Query q = session.createQuery("UPDATE BuildingsEntity SET name = '" + newBuilding.getName() + "', fullName = '" + newBuilding.getFullName() + "', town = '" + newBuilding.getTown() + "', address = '" + newBuilding.getAddress() + "', area = '" + newBuilding.getArea() + "' WHERE idBuilding = " + buildingsTable.getSelectionModel().getSelectedItem().getIdBuilding());
    q.executeUpdate();
    transaction.commit();
    initBuildingsTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Zapisano zmiany");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();

  }

  public void buildingDelete(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    Query q = session.createQuery("delete BuildingsEntity WHERE idBuilding = " + buildingsTable.getSelectionModel().getSelectedItem().getIdBuilding());
    q.executeUpdate();
    transaction.commit();
    buildingName.setText("");
    buildingFullName.setText("");
    buildingArea.setText("");
    buildingAddress.setText("");
    buildingTown.setText("");
    initBuildingsTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.WARNING, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Usunięto budynek");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();

  }

  public void initEquipmentTable() {
    Transaction transaction = session.beginTransaction();
    List<EquipmentEntity> users = session.createQuery("from EquipmentEntity ").list();
    transaction.commit();

    ObservableList<EquipmentEntity> equipmentEntities = equipmentTable.getItems();
    equipmentEntities.clear();
    equipmentEntities.addAll(users);
  }

  public void initEquipmentInfo() {
    if (equipmentTable.getSelectionModel().getSelectedItem() != null) {
      ObservableList<EquipmentEntity> equipmentEntities = equipmentTable.getItems();
      EquipmentEntity selectedEquipment = equipmentTable.getSelectionModel().getSelectedItem();
      equipmentDelete.setDisable(false);

      equipmentName.setText(selectedEquipment.getName());
      equipmentDescription.setText(selectedEquipment.getDescription());

    } else {
      equipmentName.setText("");
      equipmentDescription.setText("");
      deleteRoomType.setDisable(true);
    }
  }

  public void addNewEquipment(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    EquipmentEntity newEquipment = new EquipmentEntity();
    newEquipment.setName(equipmentName.getText());
    newEquipment.setDescription(equipmentDescription.getText());

    session.save(newEquipment);
    transaction.commit();
    initEquipmentTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Dodano nowe wyposażenie");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void saveEquipment(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    EquipmentEntity newEquipment = new EquipmentEntity();
    newEquipment.setName(equipmentName.getText());
    newEquipment.setDescription(equipmentDescription.getText());

    Query q = session.createQuery("UPDATE EquipmentEntity SET name = '" + newEquipment.getName() + "', description = '" + newEquipment.getDescription() + "' WHERE idEquipment = " + equipmentTable.getSelectionModel().getSelectedItem().getIdEquipment());
    q.executeUpdate();
    transaction.commit();
    initEquipmentTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Zapisano zmiany");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void deleteEquipment(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    Query q = session.createQuery("delete EquipmentEntity WHERE idEquipment = " + equipmentTable.getSelectionModel().getSelectedItem().getIdEquipment());
    q.executeUpdate();
    transaction.commit();
    equipmentName.setText("");
    equipmentDescription.setText("");
    initEquipmentTable();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.WARNING, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Usunięto wyposażenie");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void initEquipmentAlloc() {
    Transaction transaction = session.beginTransaction();
    List<RoomsEntity> rooms = session.createQuery("from RoomsEntity ").list();
    List<EquipmentEntity> equipments = session.createQuery("from EquipmentEntity ").list();
    transaction.commit();

    eqAllocComboRoom.getItems().clear();
    equipmentComboEq.getItems().clear();
    eqAllocComboRoom.getItems().addAll(rooms);
    equipmentComboEq.getItems().add(new EquipmentEntity(""));
    equipmentComboEq.getItems().addAll(equipments);

  }

  public void initEquipmentAllocInfo() {
    Transaction transaction = session.beginTransaction();
    session.clear();
    List<EquipmentInfoEntity> eqs = session.createQuery("from EquipmentInfoEntity WHERE idRoom = " + eqAllocComboRoom.getSelectionModel().getSelectedItem().getIdRoom()).list();
    transaction.commit();

    ObservableList<EquipmentInfoEntity> equipmentEntities = equipmentAllocTable.getItems();
    equipmentEntities.clear();
    equipmentEntities.addAll(eqs);
  }

  public void initEquipmentAllocSpecInfo() {
    if (equipmentAllocTable.getSelectionModel().getSelectedItem() != null) {
      eqAmountInput.setText(String.valueOf(equipmentAllocTable.getSelectionModel().getSelectedItem().getAmount()));
      equipmentComboEq.getSelectionModel().select(equipmentAllocTable.getSelectionModel().getSelectedItem().getIdEquipment());
      deleteEqAlloc.setDisable(false);
    } else {
      eqAmountInput.setText("");
      deleteEqAlloc.setDisable(true);
    }
  }

  public void addNewEqAlloc(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    EquipmentAllocationEntity newEquipmentAllocation = new EquipmentAllocationEntity();
    newEquipmentAllocation.setIdRoom(eqAllocComboRoom.getSelectionModel().getSelectedItem().getIdRoom());
    newEquipmentAllocation.setIdEquipment(equipmentComboEq.getSelectionModel().getSelectedItem().getIdEquipment());
    newEquipmentAllocation.setAmount(Integer.parseInt(eqAmountInput.getText()));

    session.save(newEquipmentAllocation);
    transaction.commit();
    initEquipmentAllocInfo();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Dodano nowy przydział");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void deleteEqAlloc(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    Query q = session.createQuery("delete EquipmentAllocationEntity WHERE idAlloc = " + equipmentAllocTable.getSelectionModel().getSelectedItem().getIdAlloc());
    q.executeUpdate();
    transaction.commit();
    initEquipmentAllocInfo();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.WARNING, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Usunięto przydział");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }

  public void saveEquipmentAlloc(ActionEvent actionEvent) {
    Transaction transaction = session.beginTransaction();
    session.clear();
    EquipmentAllocationEntity newEqAlloc = new EquipmentAllocationEntity();
    newEqAlloc.setIdRoom(eqAllocComboRoom.getSelectionModel().getSelectedItem().getIdRoom());
    newEqAlloc.setAmount(Integer.parseInt(eqAmountInput.getText()));
    newEqAlloc.setIdEquipment(equipmentComboEq.getSelectionModel().getSelectedItem().getIdEquipment());

    Query q = session.createQuery("UPDATE EquipmentAllocationEntity SET idRoom = " + newEqAlloc.getIdRoom() + ", amount = " + newEqAlloc.getAmount() + ", idEquipment = " + newEqAlloc.getIdEquipment() + " WHERE idAlloc = " + equipmentAllocTable.getSelectionModel().getSelectedItem().getIdAlloc());
    q.executeUpdate();
    transaction.commit();
    initEquipmentAllocInfo();

    ButtonType buttonTypeOk = new ButtonType("OK");
    Alert alert = new Alert(Alert.AlertType.NONE, " ", buttonTypeOk);
    alert.setTitle("Wykonano");
    alert.setHeaderText("Zapisano zmiany");
    alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
    alert.showAndWait();
  }
}
