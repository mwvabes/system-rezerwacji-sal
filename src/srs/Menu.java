package srs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.hibernate.sql.Select;
import srs.subtypes.BuildingChoose;
import srs.subtypes.EquipmentInfo;
import srs.subtypes.RoomInfo;
import srs.subtypes.TypeChoose;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Menu implements Initializable {

  //int idRoom, int idBuilding, String name, int seats, int maxTimeReservation, byte reservationAbility, int idType

  @FXML private TableView<RoomInfo> tableRooms;
  @FXML private ComboBox<BuildingChoose> chooseBuildingCombo;
  @FXML private ComboBox<TypeChoose> chooseTypeCombo;

  @FXML private Text buildingNameInfo;
  @FXML private Text buildingAddressInfo;
  @FXML private Text buildingAddressInfoLabel;
  @FXML private Text buildingAreaInfo;
  @FXML private Text buildingAreaInfoLabel;
  @FXML private Text buildingFullName;

  @FXML private Text floorInfo;
  @FXML private Text floorInfoLabel;

  @FXML private Text wingInfo;
  @FXML private Text wingInfoLabel;

  @FXML private Text roomFullName;
  @FXML private Text roomFullNameLabel;
  @FXML private Text roomNameInfo;
  @FXML private Text seatsInfo;
  @FXML private Text roomTypeInfo;

  @FXML private CheckBox showOnlyToReservation;

  @FXML private Text lastActionInfo;

  @FXML private TextArea equipmentDescriptionInfo;

  @FXML private TableView<EquipmentInfo> equipmentInfo;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      Connection conn = new DbConnection().connect();
      viewRoomsTable(conn, 0, 0);
      chooseBuilding(new ActionEvent());
      chooseType(new ActionEvent());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    tableRooms.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      try {
        getRoomSpecificInfo();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });

    showOnlyToReservation.selectedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

        if (showOnlyToReservation.isSelected()) {
          lastActionInfo.setText("Wyświetlono tylko dostępne do rezerwacji");
        }
        else {
          lastActionInfo.setText("Wyświetlono wszystkie dostępne sale");
        }

        try {
          buildingAndTypeChoosed(new ActionEvent());
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    });

  }

  @FXML
  protected void showLogowanie(ActionEvent event) {
    SceneManager.renderScene("logowanie");
  }

  @FXML
  protected void chooseBuilding(ActionEvent event) throws SQLException {
    Connection conn = new DbConnection().connect();

    Statement stmt = null;
    String query = "select ID_building, name, area from srs.buildings";
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ObservableList<BuildingChoose> chooseBuilding = chooseBuildingCombo.getItems();
      chooseBuilding.add(new BuildingChoose("Wszystkie budynki"));

      while (rs.next()) {
        BuildingChoose b = new BuildingChoose(rs.getInt("ID_building"), rs.getString("name"), rs.getString("area"));
        chooseBuilding.add(b);
      }
      chooseBuildingCombo.getSelectionModel().select(0);
    } catch (SQLException e ) {
      System.out.println(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }

  }

  @FXML
  protected void chooseType(ActionEvent event) throws SQLException {
    Connection conn = new DbConnection().connect();

    Statement stmt = null;
    String query = "select ID_type, name from srs.room_types";
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ObservableList<TypeChoose> chooseType = chooseTypeCombo.getItems();
      //chooseType.clear();
      chooseType.add(new TypeChoose("Wszystkie typy"));
      while (rs.next()) {
        TypeChoose t = new TypeChoose(rs.getInt("ID_type"), rs.getString("name"));
        chooseType.add(t);
      }
      chooseTypeCombo.getSelectionModel().select(0);
    } catch (SQLException e ) {
      System.out.println(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }

  }

  void viewRoomsTable(Connection conn, int building, int type) throws SQLException {

    Statement stmt = null;
    String query = "";
    if (building == 0 && type == 0) {
      query = "select rooms.ID_room, rooms.name, rooms.seats, room_types.name FROM srs.rooms, srs.room_types WHERE rooms.id_type = room_types.id_type";
    } else if (building != 0 && type == 0) {
      query = "select rooms.ID_room, rooms.name, rooms.seats, room_types.name FROM srs.rooms, srs.room_types WHERE rooms.id_type = room_types.id_type AND rooms.ID_building = " + building;
    } else if (building == 0 && type != 0) {
      query = "select rooms.ID_room, rooms.name, rooms.seats, room_types.name FROM srs.rooms, srs.room_types WHERE rooms.id_type = room_types.id_type AND rooms.id_type = " + type;
    } else {
      query = "select rooms.ID_room, rooms.name, rooms.seats, room_types.name FROM srs.rooms, srs.room_types WHERE rooms.id_type = room_types.id_type AND rooms.ID_building = " + building + " AND rooms.id_type = " + type;
    }

    if (showOnlyToReservation.isSelected()) {
      query += " AND rooms.reservation_ability = 1";
    }

    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      ObservableList<RoomInfo> rooms = tableRooms.getItems();
      rooms.clear();
      while (rs.next()) {
        RoomInfo room = new RoomInfo(rs.getInt("rooms.ID_room"), rs.getString("rooms.name"), rs.getInt("rooms.seats"), rs.getString("room_types.name"));

        rooms.add(room);
      }
    } catch (SQLException e ) {
      System.out.println(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }

    tableRooms.getSelectionModel().clearSelection();

  }

  @FXML
  void buildingAndTypeChoosed(ActionEvent event) throws SQLException {
    ObservableList<RoomInfo> rooms = tableRooms.getItems();
    BuildingChoose buildingChoosed = new BuildingChoose("");
    TypeChoose typeChoosed = new TypeChoose("");
    if (chooseBuildingCombo.getSelectionModel().getSelectedIndex() >= 0) {
      buildingChoosed = chooseBuildingCombo.getSelectionModel().getSelectedItem();
    }
    if (chooseTypeCombo.getSelectionModel().getSelectedIndex() >= 0) {
      typeChoosed = chooseTypeCombo.getSelectionModel().getSelectedItem();
    }
    viewRoomsTable(new DbConnection().connect(), buildingChoosed.getIdBuilding(),
        typeChoosed.getIdType());
  }

  @FXML
  private void getRoomSpecificInfo() throws SQLException {

    if (tableRooms.getSelectionModel().getSelectedItem() != null) {
      RoomInfo selectedRoom = tableRooms.getSelectionModel().getSelectedItem();
      Connection conn = new DbConnection().connect();

      Statement stmt = null;
      String query = "select * from rooms, buildings, room_types WHERE ID_room = " + selectedRoom.getIdRoom() + " AND rooms.ID_building = buildings.ID_building AND rooms.id_type = room_types.id_type";
      try {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if(rs.next()) {
          buildingNameInfo.setText(rs.getString("buildings.name"));
          roomNameInfo.setText(rs.getString("rooms.name"));
          seatsInfo.setText(String.valueOf(rs.getInt("rooms.seats")));

          if (!rs.getString("rooms.floor").equals("-")) {
            floorInfo.setText(rs.getString("rooms.floor"));
            floorInfoLabel.setText("Piętro");
          } else {
            floorInfo.setText(" ");
            floorInfoLabel.setText("--");
          }

          if (!rs.getString("rooms.wing").equals("-")) {
            wingInfo.setText(rs.getString("rooms.wing"));
            wingInfoLabel.setText("Skrzydło");
          } else {
            wingInfo.setText(" ");
            wingInfoLabel.setText("--");
          }

          if (!rs.getString("rooms.fullName").equals("-")) {
            roomFullName.setText(rs.getString("rooms.fullName"));
            roomFullNameLabel.setText("Nazwa");
          } else {
            roomFullName.setText(" ");
            roomFullNameLabel.setText("--");
          }

          if (!rs.getString("buildings.fullName").equals("-")) {
            roomFullName.setText(rs.getString("rooms.fullName"));
            roomFullNameLabel.setText("Nazwa");
          } else {
            roomFullName.setText(" ");
            roomFullNameLabel.setText("--");
          }

          if (!rs.getString("buildings.address").equals("-")) {
            buildingAddressInfo.setText(rs.getString("buildings.address"));
            buildingAddressInfoLabel.setText("Adres");
          } else {
            buildingAddressInfo.setText(" ");
            buildingAddressInfoLabel.setText("--");
          }

          if (!rs.getString("buildings.area").equals("-")) {
            buildingAreaInfo.setText(rs.getString("buildings.area"));
            buildingAreaInfoLabel.setText("Rejon");
          } else {
            buildingAreaInfo.setText(" ");
            buildingAreaInfoLabel.setText("--");
          }

          if (!rs.getString("room_types.name").equals("-")) {
            roomTypeInfo.setText(rs.getString("room_types.name"));
          } else {
            roomTypeInfo.setText(" ");
          }

          if (!rs.getString("buildings.fullName").equals("-")) {
            buildingFullName.setText(rs.getString("buildings.fullName"));
          } else {
            buildingFullName.setText(" ");
          }

          showEquipmentInfo(rs.getInt("rooms.ID_room"));
        }

      } catch (SQLException e ) {
        System.out.println(e);
      } finally {
        if (stmt != null) { stmt.close(); }
      }

    }
    else {
      buildingNameInfo.setText("--");
      roomNameInfo.setText("-");
      seatsInfo.setText(("--"));
        floorInfo.setText(" ");
        floorInfoLabel.setText("--");
        wingInfo.setText(" ");
        wingInfoLabel.setText("--");
        roomFullName.setText(" ");
        roomFullNameLabel.setText("--");
        roomFullName.setText(" ");
        roomFullNameLabel.setText("--");
        buildingAddressInfo.setText(" ");
        buildingAddressInfoLabel.setText("--");
        buildingAreaInfo.setText(" ");
        buildingAreaInfoLabel.setText("--");
        roomTypeInfo.setText(" ");
        buildingFullName.setText(" ");
    }
  }

  void showEquipmentInfo(int ID_room) throws SQLException {
    Connection conn = new DbConnection().connect();

    Statement stmt = null;
    String query = "";
    query = " SELECT * from rooms, equipment_allocation, equipment WHERE rooms.ID_room = equipment_allocation.ID_room AND equipment_allocation.ID_equipment = equipment.ID_equipment AND rooms.ID_room = " + ID_room;

    ObservableList<EquipmentInfo> equipmentInfos = equipmentInfo.getItems();

    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      equipmentInfos.clear();
      while (rs.next()) {
        EquipmentInfo equipmentInfo = new EquipmentInfo(rs.getInt("equipment.ID_equipment"), rs.getString("equipment.name"), rs.getString("equipment.description"), rs.getInt("equipment_allocation.amount"));

        equipmentInfos.add(equipmentInfo);

      }
    } catch (SQLException e ) {
      System.out.println(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }

    equipmentInfo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (equipmentInfo.getSelectionModel().getSelectedItem() != null) {
        equipmentDescriptionInfo.setText(equipmentInfo.getSelectionModel().getSelectedItem().getEqDescription());
      }
      else {
        equipmentDescriptionInfo.setText("");
      }
    });

  }


}
