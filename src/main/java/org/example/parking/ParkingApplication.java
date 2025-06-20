package org.example.parking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ParkingApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.setStage(stage);

        FXMLLoader fxmlLoader = new FXMLLoader(ParkingApplication.class.getResource("parking-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Image image = new Image("file:src/main/resources/img/mano.png");
        scene.setCursor(new ImageCursor(image));
        stage.setTitle("Gestor de Parking");
        stage.setScene(scene);

        stage.show();
    }
}