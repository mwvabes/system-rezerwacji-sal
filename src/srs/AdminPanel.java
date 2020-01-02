package srs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanel implements Initializable {
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  protected void showLogowanie(ActionEvent event) {
    SceneManager.renderScene("logowanie");
  }

}
