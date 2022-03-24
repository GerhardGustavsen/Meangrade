package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.User;

public class DashboardController {

  User user;

  private void openGrades(String data) {
    Parent root;
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Grades.fxml"));
      root = loader.load();
      GradesController controller = loader.getController();
      controller.passUser(user);

      Stage stage = new Stage();
      stage.setTitle("Grades");
      stage.setScene(new Scene(root));
      stage.show();

    } catch (IOException e) {
      System.out.print("Did not find data fxml form!");
      e.printStackTrace();
    }
  }

  public void initialize() {

  }

  public void passUser(User u) {
    user = u;
  }
}
