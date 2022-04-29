package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.Core;

abstract class Controller {

  protected Core core;
  protected Stage stage;

  protected void openFXML(Controller controller, String fxmlPath) {

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));

      loader.setController(controller);
      controller.sendCore(core);
      controller.sendStage(stage);
      Parent root = loader.load();
      controller.Poppulate();

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

  private void sendCore(Core c) {
    core = c;
  }

  private void sendStage(Stage s) {
    stage = s;
  }

  abstract void Poppulate();
}
