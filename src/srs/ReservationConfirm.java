package srs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationConfirm implements Initializable {


  @FXML private Text confirm1;
  @FXML private Text confirm2;
  @FXML private Text confirm3;
  @FXML private Text confirm4;
  @FXML private Text roomName;
  @FXML private Text timeFrom;
  @FXML private Text timeTo;
  @FXML private Text confirmAlert;

  @FXML private Button confirmExit;
  @FXML private Button confirmYes;
  @FXML private Button confirmCancel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      setUpConfirmation();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void setUpConfirmation() throws IOException {

    FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(
        "/srs/menu.fxml"));
    Parent root = (Parent) loader.load();
    Menu menu = loader.getController();

    timeFrom.setText(menu.getDateFromSelected() + " " + menu.getTimeFromSelected());
    timeTo.setText(menu.getDateToSelected() + " " + menu.getTimeToSelected());
    roomName.setText(menu.getRoomNameSelected());

  }

}
