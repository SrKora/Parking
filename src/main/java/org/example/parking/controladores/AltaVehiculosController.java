package org.example.parking.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.parking.Coche;
import org.example.parking.SceneManager;

public class AltaVehiculosController {

    @FXML
    private ComboBox<String> comboBoxAlta;
    @FXML
    private TextField inputMatricula;
    @FXML
    private Label createdLabel;

    public void initialize() {
        comboBoxAlta.getItems().addAll("Oficial", "Residente", "No residente");
        comboBoxAlta.setValue("Oficial");

    }

    @FXML
    protected void onDarAltaclick() {
        int resultado = switch (comboBoxAlta.getValue()) {
            case "Oficial" -> 0;
            case "Residente" -> 1;
            case "No Residente" -> 2;
            default -> 3;
        };
        if (inputMatricula.getText().matches("[0-9]{1,4}[A-Z]{1,3}")) {
            Coche c = new Coche(inputMatricula.getText(), resultado);
            createdLabel.setText("Vehículo dado de alta!");
            switch (resultado) {
                case 0:

                    break;
                case 1:

                    break;
                case 2:

                    break;
                default:
                    break;
            }
        } else {
            createdLabel.setText("La matrícula no se ha introducido correctamente");
            inputMatricula.setText("");
        }
    }

    @FXML
    protected void onVolverClick() {
        SceneManager.cambiarEscena("parking-view.fxml");
    }
}


