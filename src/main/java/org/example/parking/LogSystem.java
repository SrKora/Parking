package org.example.parking;

import javafx.scene.control.Alert;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogSystem {

    private final DateTimeFormatter fechaFormatContentLog = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
    private final DateTimeFormatter fechaFormatLogName = DateTimeFormatter.ofPattern("dd-MM-yy");
    private final LocalDateTime fecha = LocalDateTime.now();

    public static void errorLog (String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Matricula incorrecta");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void infoLog(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFORMATION");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public String errorLogFile(String error) {
        String cadena = "";
        try (FileWriter fw = new FileWriter("Log - " + fecha.format(fechaFormatLogName) + ".txt", true)) {
            cadena = "Error: " + error + " Date: " + fecha.format(fechaFormatContentLog) + "\n";
            fw.write(cadena);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cadena;
    }

    public void infoLogFile(String info) {
        String cadena;
        try (FileWriter fw = new FileWriter("Log - " + fecha.format(fechaFormatLogName) + ".txt", true)) {
            cadena = "INFO: " + info + " Date: " + fecha.format(fechaFormatContentLog) + "\n";
            fw.write(cadena);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}