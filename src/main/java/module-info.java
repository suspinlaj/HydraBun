module com.susana.hydrabum {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.susana.hydrabum to javafx.fxml;
    exports com.susana.hydrabum;
    exports controlador;
    opens controlador to javafx.fxml;
    exports modelo;
    opens modelo to javafx.fxml;
}