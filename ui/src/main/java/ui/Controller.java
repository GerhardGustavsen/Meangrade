package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.Core;
import core.Grade;
import core.User;

public class Controller {

  Core core;
  Stage stage;

  protected void openFXML(Controller controller, String fxmlPath) {

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

      loader.setController(controller);
      controller.sendCore(core);
      controller.sendStage(stage);
      Parent root = loader.load();

      if (core.getActiveUser() != null)
        stage.setTitle("User: " + core.getActiveUser().getName());
      else
        stage.setTitle("MeanGrade");

      stage.setScene(new Scene(root));
    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }
  }

  public void sendCore(Core c) {
    core = c;
  }

  private void sendStage(Stage s) {
    stage = s;
  }
}
