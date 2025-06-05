package org.example.parking.controladores;

import javafx.fxml.FXML;
import org.example.parking.SceneManager;

public class RegistrarSalidaController {

    @FXML
    protected void onVolverClick() {
        SceneManager.cambiarEscena("parking-view.fxml");
    }
}
