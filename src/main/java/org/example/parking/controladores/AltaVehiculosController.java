package org.example.parking.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.parking.Coche;
import org.example.parking.LogSystem;
import org.example.parking.Parking;
import org.example.parking.SceneManager;

public class AltaVehiculosController {

    LogSystem log = new LogSystem();

    @FXML
    private ComboBox<String> comboBoxAlta;
    @FXML
    private TextField inputMatricula;
    @FXML
    private Label createdLabel;
    @FXML
    private ListView<String> listCochesOficiales;
    @FXML
    private ListView<String> listCochesResidentes;
    @FXML
    private ListView<String> listCochesNOResidentes;

    public void initialize() {
        comboBoxAlta.getItems().addAll("Oficial", "Residente", "No residente");
        comboBoxAlta.setValue("Oficial");

        Parking.parking.forEach(coche -> {
            switch (coche.getResidente()) {
                case 0 -> listCochesOficiales.getItems().add(coche.toStringSinFecha());
                case 1 -> listCochesResidentes.getItems().add(coche.toStringSinFecha());
                case 2 -> listCochesNOResidentes.getItems().add(coche.toStringSinFecha());
            }
        });
    }

    @FXML
    protected void onDarAltaclick() {
        int resultado = switch (comboBoxAlta.getValue()) {
            case "Oficial" -> 0;
            case "Residente" -> 1;
            case "No residente" -> 2;
            default -> 3;
        };

        if (inputMatricula.getText().matches("[0-9]{1,4}[A-Z]{1,3}")) {
            if (!Parking.comprobarMatricula(inputMatricula.getText())) {
                Coche c = new Coche(inputMatricula.getText(), resultado);
                LogSystem.infoLog("Vehículo dado de alta!");
                setOnCarList(resultado, c);
                inputMatricula.setText("");
            } else if (Parking.comprobarMatricula(inputMatricula.getText()) && Parking.buscarCoche(inputMatricula.getText()).getResidente() == 3) {
                Coche c = Parking.buscarCoche(inputMatricula.getText());
                LogSystem.infoLog("Vehículo dado de alta!");
                c.setResidente(resultado);
                setOnCarList(resultado, c);
                inputMatricula.setText("");
            } else {
                LogSystem.errorLog("Este coche ya ha sido introducido anteriormente");
                log.errorLogFile("Este coche ya ha sido introducido " + inputMatricula.getText());
                inputMatricula.setText("");
            }
        } else {
            LogSystem.errorLog("La matrícula no se ha introducido correctamente");
            log.errorLogFile("La matrícula no se ha introducido correctamente" + inputMatricula.getText());
            inputMatricula.setText("");
        }
    }

    private void setOnCarList(int resultado, Coche c) {
        switch (resultado) {
            case 0:
                listCochesOficiales.getItems().add(c.toStringSinFecha());
                log.infoLogFile("Vehículo oficial dado de alta " + c.toStringSinFecha());
                break;
            case 1:
                listCochesResidentes.getItems().add(c.toStringSinFecha());
                log.infoLogFile("Vehículo residente dado de alta " + c.toStringSinFecha());
                break;
            case 2:
                listCochesNOResidentes.getItems().add(c.toStringSinFecha());
                log.infoLogFile("Vehículo no residente dado de alta " + c.toStringSinFecha());
                break;
        }

        if (!Parking.parking.contains(c)) {
            Parking.parking.add(c);
        }
    }

    @FXML
    protected void onVolverClick() {
        SceneManager.cambiarEscena("parking-view.fxml");
    }
}