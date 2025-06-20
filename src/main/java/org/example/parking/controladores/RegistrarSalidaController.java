package org.example.parking.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.parking.Coche;
import org.example.parking.LogSystem;
import org.example.parking.Parking;
import org.example.parking.SceneManager;

import java.time.LocalDateTime;

public class RegistrarSalidaController {
    LogSystem log = new LogSystem();
    @FXML
    private ListView<String> salidasListView;
    @FXML
    private TextField inputMatricula;
    @FXML
    private Label salidaLabel;

    public void initialize() {
        salidasListView.getItems().addAll(Parking.parking.stream().map(Coche::toStringConResidente).toList());
    }

    @FXML
    protected void onVolverClick() {
        SceneManager.cambiarEscena("parking-view.fxml");
    }

    @FXML
    protected void onSalidaDeCocheClick() {
        if (inputMatricula.getText().matches("[0-9]{1,4}[A-Z]{1,3}")) {
            if (Parking.comprobarMatricula(inputMatricula.getText())) {
                switch (Parking.buscarCoche(inputMatricula.getText()).getResidente()) {
                    case 0:
                        Parking.buscarCoche(inputMatricula.getText()).setHoraSalida(LocalDateTime.now());
                        salidasListView.getItems().removeIf(salida -> salida.contains(inputMatricula.getText()));
                        log.infoLogFile("Coche oficial saliendo " + inputMatricula.getText());
                        break;
                    case 1:
                        Parking.buscarCoche(inputMatricula.getText()).setHoraSalida(LocalDateTime.now());
                        salidasListView.getItems().removeIf(salida -> salida.contains(inputMatricula.getText()));
                        log.infoLogFile("Coche residente saliendo " + inputMatricula.getText());
                        break;
                    case 2, 3:
                        Parking.buscarCoche(inputMatricula.getText()).setHoraSalida(LocalDateTime.now());
                        Parking.buscarCoche(inputMatricula.getText()).setDeuda();
                        salidaLabel.setText("Importe a pagar: " + Parking.buscarCoche(inputMatricula.getText()).getDeuda() + "€");
                        salidasListView.getItems().removeIf(salida -> salida.contains(inputMatricula.getText()));
                        log.infoLogFile("Coche no residente saliendo " + inputMatricula.getText() + " Importe a pagar: " + Parking.buscarCoche(inputMatricula.getText()).getDeuda() + "€");
                        break;
                    default:
                        break;
                }
                Parking.parking.remove(Parking.buscarCoche(inputMatricula.getText()));
            } else {
                LogSystem.errorLog("Esta matricula no se ha encontrado");
                log.errorLogFile("Esta matricula no se ha encontrado " + inputMatricula.getText());
                inputMatricula.setText("");
            }
        } else {
            LogSystem.errorLog("La matrícula no se ha introducido correctamente");
            log.errorLogFile("La matrícula no se ha introducido correctamente " + inputMatricula.getText());
            inputMatricula.setText("");
        }
    }
}