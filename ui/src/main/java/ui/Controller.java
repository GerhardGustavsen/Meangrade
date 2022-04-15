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

  protected void openFXML(Stage stage, String fxmlPath) {

    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
      root = loader.load();

      Controller controller = loader.getController();
      controller.sendCore(core);

      stage.setTitle("User: " + core.getActiveUser());
      stage.setScene(new Scene(root));
      stage.show();

    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }
  }

  private void sendCore(Core c) {
    core = c;
  }
}
