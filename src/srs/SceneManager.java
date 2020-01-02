package srs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Hashtable;

public class SceneManager {

  private static Stage stage;
  private static Stage stageTemp;
  private static Hashtable<String, String> view = new Hashtable<>();

  public static void addScene(String name, String path) throws IOException {
    view.put(name, path);
  }

  public static void removeScene(String name) {
    view.remove(name);
  }

  public static void renderScene(String name) {
    String path = "";
    try {
      path = view.get(name);
      Parent root = FXMLLoader.load(SceneManager.class.getResource(path));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      System.err.println("Nie można załadować pliku XML z widokiem: " + path);
      e.printStackTrace();
    } catch (RuntimeException e) {
      System.err.println("Nazwa widoku jest nieprawidłowa");
    }
  }

  public static void setStage(Stage _stage) {
    stage = _stage;
  }

}
