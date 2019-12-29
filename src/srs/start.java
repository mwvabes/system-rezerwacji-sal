package srs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class start extends Application {

  public void start(Stage primaryStage) throws IOException {

    SceneManager.setStage(primaryStage);
    SceneManager.addScene("logowanie", "/srs/logowanie.fxml");
    SceneManager.addScene("menu", "/srs/menu.fxml");
    SceneManager.addScene("sala", "/srs/sala.fxml");

    SceneManager.renderScene("logowanie");
  }


  public static void main(String[] args) {
    launch(args);
  }

}
