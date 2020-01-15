package gui;

import entities.RoomsEntity;
import entities.UsersEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import setUp.SetUpConf;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Login implements Initializable {

  @FXML
  Button closeButton;
  @FXML
  TextField username;
  @FXML
  TextField password;
  @FXML
  Text wrongLogin;
  @FXML Text infoAvailableForReservation;
  @FXML Text infoConnectionIsValid;

  Session session;
  SetUpConf setUpConf;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setUpConf = new SetUpConf();
    session = setUpConf.setUpStart();
    Transaction transaction = session.beginTransaction();
    List<RoomsEntity> rooms = session.createQuery("from RoomsEntity ").list();
    infoAvailableForReservation.setText("Sal dostępnych do rezerwacji: " + rooms.size());
    infoConnectionIsValid.setText("Połączenie z bazą danych prawidłowe");


    transaction.commit();

//    setUpConf.setUpClose();
  }

  @FXML
  void closeStage() {

    setUpConf.setUpClose();
    Stage stage = (Stage) closeButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  void logMeInFunction() throws SQLException, IOException {

    wrongLogin.setText("Logowanie...");
    System.out.println("a");

    String loginVal = username.getText();
    String passwordVal = password.getText();

//    SetUpConf setUpConf = new SetUpConf();
//    Session session = setUpConf.setUpStart();
    Transaction transaction = session.beginTransaction();

    List<UsersEntity> logged = session.createQuery("from UsersEntity where username = '" + loginVal + "' AND password = '" + passwordVal + "'").list();
    transaction.commit();

//    setUpConf.setUpClose();

    if (logged.isEmpty()) {
      wrongLogin.setText("Niepoprawny login lub/i hasło");
    } else {
      wrongLogin.setText("Logowanie poprawne!");
      srs.SceneManager.renderMenuUserLogged(logged.get(0));
      setUpConf.setUpClose();
    }

  }


}
