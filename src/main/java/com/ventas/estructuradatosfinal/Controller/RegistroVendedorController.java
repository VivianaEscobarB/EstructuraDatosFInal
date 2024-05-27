package com.ventas.estructuradatosfinal.Controller;
import com.ventas.estructuradatosfinal.Listas.ListaSimple;
import com.ventas.estructuradatosfinal.Model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class RegistroVendedorController {
    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField apellidoTextField;

    @FXML
    private TextField cedulaTextField;

    @FXML
    private TextField direccionTextField;

    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField contrasenaTextField;

    private ListaSimple<Vendedor> vendedores;
    private MainController mainController;

    public void setVendedores(ListaSimple<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void crearVendedor() {
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String cedula = cedulaTextField.getText();
        String direccion = direccionTextField.getText();
        String usuario = usuarioTextField.getText();
        String contrasena = contrasenaTextField.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || cedula.isEmpty() || direccion.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlarma("Por favor complete todos los campos", Alert.AlertType.ERROR);
            return;
        }

        Vendedor vendedor = new Vendedor(nombre, apellido, cedula, direccion, usuario, contrasena);
        vendedores.agregarInicio(vendedor);
        mainController.guardarVendedores();
        mostrarAlarma("Vendedor creado", Alert.AlertType.INFORMATION);

        Stage stage = (Stage) nombreTextField.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlarma(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alarma");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
