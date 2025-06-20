package org.example.parking;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {
    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void cambiarEscena(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlPath));
            Scene scene = new Scene(root);
            Image image = new Image("file:src/main/resources/img/mano.png");
            scene.setCursor(new ImageCursor(image));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


