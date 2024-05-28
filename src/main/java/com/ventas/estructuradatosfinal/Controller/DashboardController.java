package com.ventas.estructuradatosfinal.Controller;

import com.ventas.estructuradatosfinal.Model.Vendedor;
import com.ventas.estructuradatosfinal.Model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DashboardController {

    @FXML
    private ComboBox<String> metricasComboBox;

    @FXML
    private TextField usuario1TextField;

    @FXML
    private TextField usuario2TextField;

    @FXML
    private DatePicker fechaInicioDatePicker;

    @FXML
    private DatePicker fechaFinDatePicker;

    @FXML
    private ListView<Producto> resultadosListView;

    private Vendedor vendedor; // Referencia al vendedor

    private ObservableList<String> metricas = FXCollections.observableArrayList(
            "Cantidad de productos publicados entre determinada fecha",
            "Cantidad de productos publicados por usuario"
    );

    @FXML
    public void initialize() {
        metricasComboBox.setItems(metricas);
    }

    @FXML
    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @FXML
    public void generarReporte() {
        String metricaSeleccionada = metricasComboBox.getValue();
        String usuario1 = usuario1TextField.getText();
        String usuario2 = usuario2TextField.getText();
        LocalDate fechaInicio = fechaInicioDatePicker.getValue();
        LocalDate fechaFin = fechaFinDatePicker.getValue();

        List<Producto> resultados = new ArrayList<>();

        switch (metricaSeleccionada) {
            case "Cantidad de productos publicados entre determinada fecha":
                resultados = calcularProductosPublicadosEntreFechas(fechaInicio, fechaFin);
                break;
            case "Cantidad de productos publicados por usuario":
                resultados = calcularProductosPublicadosPorUsuario(usuario1);
                break;
        }

        resultadosListView.setItems(FXCollections.observableArrayList(resultados));
    }

    private List<Producto> calcularProductosPublicadosEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Producto> resultados = new ArrayList<>();
        // Aquí deberías implementar la lógica para obtener los productos publicados entre las fechas dadas
        return resultados;
    }

    private List<Producto> calcularProductosPublicadosPorUsuario(String usuario) {
        List<Producto> resultados = new ArrayList<>();
        // Verificar si hay un vendedor y si tiene productos
        if (vendedor != null && vendedor.getProductos() != null) {
            for (Producto producto : vendedor.getProductos()) {
                // Verificar si el usuario del vendedor coincide con el usuario dado
                if (producto.getVendedor().getUsuario().equals(usuario)) {
                    resultados.add(producto);
                }
            }
        } else {
            mostrarAlerta("El vendedor no tiene productos", Alert.AlertType.WARNING);
        }
        return resultados;
    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
