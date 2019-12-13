package srs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


  @FXML
  private Button showSala;

  @FXML
  private Button showMenu;

  @FXML
  private Button wyloguj;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  protected void showSala(ActionEvent event) {
    SceneManager.renderScene("sala");
  }

  @FXML
  protected void showLogowanie(ActionEvent event) {
    SceneManager.renderScene("logowanie");
  }

  @FXML
  protected void showMenu(ActionEvent event) { SceneManager.renderScene("menu"); }
}
