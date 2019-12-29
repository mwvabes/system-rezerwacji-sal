package srs;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import srs.subtypes.BuildingChoose;

import javafx.scene.text.Text;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Logowanie implements Initializable {

  @FXML private Text infoAvailableForReservation;
  @FXML private Text infoConnectionIsValid;
  @FXML private Button logMeIn;
  @FXML private Button closeButton;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      infoConnectionIsValid();
      howManyForReservation();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  void infoConnectionIsValid() throws SQLException {
    Connection conn = new DbConnection().connect();

    if (conn.isValid(1)) {
      infoConnectionIsValid.setText("Połączenie z bazą danych prawidłowe");
      logMeIn.setText("Wejście do panelu rezerwacji");
      logMeIn.setDisable(false);
    } else {
      infoConnectionIsValid.setText("Brak połączenia z bazą danych");
      logMeIn.setText("Brak połączenia z bazą danych");
    }

  }

  void howManyForReservation() throws SQLException {
    Connection conn = new DbConnection().connect();

    Statement stmt = null;
    String query = "SELECT count(ID_room) from rooms WHERE reservation_ability = 1";
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);
      if(rs.next()) {
        infoAvailableForReservation.setText("Sal dostępnych do rezerwacji: " + rs.getString("count(ID_room)"));
      }

    } catch (SQLException e ) {
      System.out.println(e);
    } finally {
      if (stmt != null) { stmt.close(); }
    }
  }

  @FXML
  void closeStage() {
    Stage stage = (Stage) closeButton.getScene().getWindow();

    stage.close();
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
