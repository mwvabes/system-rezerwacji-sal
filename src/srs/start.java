package srs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class start extends Application {

/*  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));

    primaryStage.setTitle("SRS - Marcin Wielgos");
    primaryStage.setScene(new Scene(root, 800, 600));
    primaryStage.show();
  }*/

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
