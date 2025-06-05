package org.example.parking.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.parking.Coche;
import org.example.parking.SceneManager;

public class RegistrarEntradaController {

    @FXML
    private TextField inputMatricula;
    @FXML
    private Label registeredEntryLabel;

    @FXML
    protected void onRegistrarEntradaClick() {
        if (inputMatricula.getText().matches("[0-9]{1,4}[A-Z]{1,3}")) {
            Coche c = new Coche(inputMatricula.getText(), 2);
            registeredEntryLabel.setText("Entrada registrada correctamente");
        } else {
            registeredEntryLabel.setText("La matrícula no se ha introducido correctamente");
            inputMatricula.setText("");
        }
    }

    @FXML
    protected void onVolverClick() {
        SceneManager.cambiarEscena("parking-view.fxml");
    }
}
