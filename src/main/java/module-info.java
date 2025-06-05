module org.example.parking {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.parking to javafx.fxml;
    exports org.example.parking;
    exports org.example.parking.controladores;
    opens org.example.parking.controladores to javafx.fxml;
}