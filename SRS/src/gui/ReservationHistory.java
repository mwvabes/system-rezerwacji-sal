package gui;

import entities.ReservationHistoryEntity;
import entities.ReservationsViewEntity;
import entities.RoomTypesEntity;
import entities.TableRoomsEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationHistory implements Initializable {

  @FXML
  private Button closeButton;

  @FXML private Text roomName;
  @FXML private Text buildingName;

  @FXML private TableView<ReservationHistoryEntity> reservationsInfoTable;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  public void setUp(List<ReservationsViewEntity> reservationsViewEntityList, TableRoomsEntity room) {
    roomName.setText(room.getRoomName());
    buildingName.setText(room.getBuildingName());

    if (reservationsViewEntityList.size() > 0) {
      ObservableList<ReservationHistoryEntity> table = reservationsInfoTable.getItems();
      for (int i = 0; i < reservationsViewEntityList.size(); i++) {
        ReservationsViewEntity r = reservationsViewEntityList.get(i);

        table.add(new ReservationHistoryEntity(r.getUserName(), r.getUserSurname(), r.getMeetTimeStart(), r.getMeetTimeEnd()));
      }
    }

  }


  public void closeStage(ActionEvent actionEvent) {
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
  }
}
