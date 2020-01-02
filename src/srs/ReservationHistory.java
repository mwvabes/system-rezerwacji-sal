package srs;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import srs.subtypes.BuildingChoose;
import srs.subtypes.ReservationInfo;
import srs.subtypes.RoomInfo;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ReservationHistory implements Initializable {

  @FXML private Button closeButton;

  @FXML private Text roomName;
  @FXML private Text buildingName;

  @FXML private TableView<ReservationInfo> reservationsInfoTable;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }


  @FXML
  void closeStage() {
    Stage stage = (Stage) closeButton.getScene().getWindow();

    stage.close();
  }

  void showReservationInfo(int roomId) throws SQLException {
    Connection conn = new DbConnection().connect();

    ObservableList<ReservationInfo> reservationsTable = reservationsInfoTable.getItems();

    Statement stmt = null;
    String query = "select users.name, users.surname, meet_time_end, meet_time_start, buildings.name, rooms.name from srs.users, srs.reservations, srs.buildings, srs.rooms WHERE buildings.ID_building = rooms.ID_building AND rooms.ID_room = reservations.ID_room  AND users.ID_user = reservations.ID_user AND reservations.ID_room = " + roomId;
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        roomName.setText(String.valueOf(rs.getString("rooms.name")));
        buildingName.setText(String.valueOf(rs.getString("buildings.name")));
        ReservationInfo r = new ReservationInfo(rs.getString("users.name"), rs.getString("users.surname"), rs.getTimestamp("reservations.meet_time_start"), rs.getTimestamp("reservations.meet_time_end"));



        reservationsTable.add(r);
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
