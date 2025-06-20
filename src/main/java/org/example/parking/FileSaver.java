package org.example.parking;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileSaver {

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyy");

    public void guardarArchivo(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");

        fileChooser.setInitialFileName("InformeResidentes_" + LocalDateTime.now().format(formato) + ".txt");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt")
        );

        File archivo = fileChooser.showSaveDialog(stage);

        if (archivo != null) {
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(String.format("%-12s | %-25s | %-15s%n", "Matrícula", "Tiempo estacionado (min)", "Cantidad a pagar"));
                writer.write("---------------------------------------------------------------\n");

                for (Coche c : Parking.parking) {
                    if (c.getResidente() == 1) {
                        c.setHoraSalida(LocalDateTime.now());
                        c.setDeuda();
                        int minutos = c.calcularTiempoMin(c.getHoraEntrada(), c.getHoraSalida());
                        float deuda = c.getDeuda();

                        writer.write(String.format("%-12s | %-25d | %-15.2f%n", c.getMatricula(), minutos, deuda));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}