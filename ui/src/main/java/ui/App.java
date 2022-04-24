package ui;

import core.Encrypt;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.Core;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loggin.fxml"));
        LogginController controller = new LogginController();
        loader.setController(controller);
        Parent root = loader.load();

        controller.core = new Core();
        controller.stage = stage;



        stage.setTitle("MeanGrade");
        stage.setScene(new Scene(root));
        stage.show();

        String test = "123";
        String encrypt = Encrypt.encrypt(test, "123");
        String decrypt = Encrypt.decrypt(encrypt, "123");
        System.out.println(encrypt);
        System.out.println(decrypt);


    }

    public static void main(String[] args) {
        launch(App.class, args);
    }

}