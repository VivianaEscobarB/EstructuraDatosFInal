package com.ventas.estructuradatosfinal.Controller;

import com.ventas.estructuradatosfinal.Model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;
import java.time.LocalDateTime;

public class VendedorController {
    private Vendedor vendedor;

    @FXML
    private ListView<String> productosListView;
    @FXML
    private TextField nomProducto;
    @FXML
    private TextField precProducto;

    private String FILENAME = "";

    @FXML
    public void agregarProducto() {
        String nombreProducto = nomProducto.getText();
        double precio = Double.parseDouble(precProducto.getText());
        productosListView.getItems().add(nombreProducto + " - " + precio+"$"+ " - " + fechaActual());
        limpiarCampos();
        guardarProductos();
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        this.FILENAME = "Productos/" + vendedor.getNombre() + ".txt";
        cargarProductos();
    }

    private void guardarProductos() {
        File dir = new File("Productos");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String producto : productosListView.getItems()) {
                writer.write(producto);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarProductos() {
        productosListView.getItems().clear();
        File file = new File(FILENAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String producto;
                while ((producto = reader.readLine()) != null) {
                    productosListView.getItems().add(producto);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void limpiarCampos() {
        nomProducto.clear();
        precProducto.clear();
    }

    private String fechaActual() {
        LocalDateTime fecha = LocalDateTime.now();
        return fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
    }
}
