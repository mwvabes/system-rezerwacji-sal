package srs;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import srs.subtypes.BuildingChoose;

import javafx.scene.text.Text;
import srs.subtypes.UserInfo;

import java.io.IOException;
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

  @FXML private TextField username;
  @FXML private PasswordField password;
  @FXML private Text wrongLogin;

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
      logMeIn.setText("Zaloguj");
      logMeIn.setDisable(false);
    } else {
      infoConnectionIsValid.setText("Brak połączenia z bazą danych");
      logMeIn.setText("Brak połączenia z bazą danych");
    }

  }

  @FXML
  void logMeInFunction() throws SQLException {

    String loginVal = username.getText();
    String passwordVal = password.getText();

    Connection conn = new DbConnection().connect();

    Statement stmt = null;
    String query = "select * from users WHERE username = '" + loginVal + "' AND password = '" + passwordVal + "'";
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      if (rs.next()) {
        wrongLogin.setText(" Logowanie... ");
        UserInfo currentLoggedUser = new UserInfo(rs.getInt("ID_user"), rs.getString("name"), rs.getString("surname"), rs.getInt("reservation_ability"), rs.getString("description"), rs.getInt("role"), rs.getString("username"));
        showMenu(new ActionEvent(), currentLoggedUser);
      } else {
        wrongLogin.setText("Błędny login lub hasło!");
      }
    } catch (SQLException | IOException e) {
      System.out.println(e);
    } finally {
      if (stmt != null) {
        stmt.close();
      }
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
  protected void showMenu(ActionEvent event, UserInfo user) throws IOException {

      SceneManager.renderMenuUserLogged(user);

  }

}
