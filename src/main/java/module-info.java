module com.ventas.estructuradatosfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.ventas.estructuradatosfinal to javafx.fxml;
    exports com.ventas.estructuradatosfinal;
    exports com.ventas.estructuradatosfinal.Controller;
    opens com.ventas.estructuradatosfinal.Controller to javafx.fxml;
}