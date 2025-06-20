package org.example.parking.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.parking.Coche;
import org.example.parking.LogSystem;
import org.example.parking.Parking;
import org.example.parking.SceneManager;

public class RegistrarEntradaController {

    LogSystem log = new LogSystem();
    @FXML
    private TextField inputMatricula;
    @FXML
    private ListView<String> list;

    public void initialize() {
        list.getItems().addAll(Parking.parking.stream().map(Coche::toString).toList());
    }

    @FXML
    protected void onRegistrarEntradaClick() {
        if (inputMatricula.getText().matches("[0-9]{1,4}[A-Z]{1,3}")) {
            if (!Parking.comprobarMatricula(inputMatricula.getText())) {
                Coche c = new Coche(inputMatricula.getText(), 3);
                log.infoLogFile("Entrada registrada correctamente " + c.toStringSinFecha());
                list.getItems().add(c.toString());
                Parking.parking.add(c);
                inputMatricula.setText("");
            } else {
                LogSystem.errorLog("Esta matricula ya ha sido registrada");
                log.errorLogFile("Esta matricula ya ha sido registrada " + inputMatricula.getText());
                inputMatricula.setText("");
            }
        } else {
            LogSystem.errorLog("La matrícula no se ha introducido correctamente");
            log.errorLogFile("La matrícula no se ha introducido correctamente " + inputMatricula.getText());
            inputMatricula.setText("");
        }
    }

    @FXML
    protected void onVolverClick() {
        SceneManager.cambiarEscena("parking-view.fxml");
    }
}