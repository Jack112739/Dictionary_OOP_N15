package main;
/**
 * ham main load sample.fxml
 */

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;

import java.nio.file.Paths;
import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.backend.DictionaryManagement;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        try {
            File remove = new File("data/OfflineDictionary.txt");
            remove.delete();
            File renew = new File("data/OfflineDictionary.txt");
            DictionaryManagement.RenewDictionary(renew);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(
                Objects.requireNonNull(this.getClass().getResource("controlMain.fxml")));
            primaryStage.setTitle("Ứng Dụng Từ Điển Anh - Việt đơn giản");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
