package org.example.parking.controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.parking.SceneManager;

public class ParkingController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onRegistrarEntradaClick() {
        SceneManager.cambiarEscena("registrarEntrada-view.fxml");
    }

    @FXML
    protected void onRegistrarSalidaClick() {
        SceneManager.cambiarEscena("registrarSalida-view.fxml");
    }

    @FXML
    protected void onAltaVehiculosClick() {
        SceneManager.cambiarEscena("altaVehiculos-view.fxml");
    }

    @FXML
    protected void onComenzarMesClick() {

    }

    @FXML
    protected void onPagosResidentesClick() {
        
    }

    @FXML
    protected void onSalirClick() {
        Platform.exit();
    }
}