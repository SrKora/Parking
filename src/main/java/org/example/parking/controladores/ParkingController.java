package org.example.parking.controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.parking.FileSaver;
import org.example.parking.LogSystem;
import org.example.parking.Parking;
import org.example.parking.SceneManager;

import java.time.LocalDateTime;

public class ParkingController {

    LogSystem log = new LogSystem();

    @FXML
    private Button pagosResidentesButton;

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
        log.infoLogFile("Mes comenzado");
        for (int i = 0; i < Parking.parking.size(); i++) {
            switch (Parking.parking.get(i).getResidente()) {
                case 0:
                    Parking.parking.remove(i);
                    break;
                case 1:
                    Parking.parking.get(i).setHoraEntrada(LocalDateTime.now());
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    protected void onPagosResidentesClick() {
        Stage stage = (Stage) pagosResidentesButton.getScene().getWindow();

        FileSaver saver = new FileSaver();
        saver.guardarArchivo(stage);

        log.infoLogFile("Informe pagos de residentes");
    }

    @FXML
    protected void onSalirClick() {
        Platform.exit();
    }
}