package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.CourseManager;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loggin.fxml"));
        Parent root = loader.load();

        stage.setTitle("MeanGrade");

        stage.setScene(new Scene(root));
        stage.show();

        // LogginController controller = loader.getController();
        // controller.setCore();
        // controller.clear();

        // CourseManager.read();
    }

    public static void main(String[] args) {
        launch(App.class, args);
    }

}