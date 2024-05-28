package com.ventas.estructuradatosfinal.Controller;

import com.ventas.estructuradatosfinal.Listas.ListaSimple;
import com.ventas.estructuradatosfinal.Model.Vendedor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private TextField usuarioTextField;

    @FXML
    private PasswordField contrasenaTextField;

    @FXML
    private ListView<String> muroListView;

    @FXML
    private ListView<String> contactosListView;

    @FXML
    private ListView<String> productosListView;

    @FXML
    private TextField mensajeTextField;

    @FXML
    private Tab vende;

    private Vendedor vendedorActual;

    @FXML
    private Button cerrar;

    @FXML
    private Button crear;

    private ListaSimple<Vendedor> vendedores = new ListaSimple<>();

    private static final String FILENAME = "muro.txt";
    private static final String VENDEDORES_FILE = "vendedores.txt";
    private static final String PRODUCTOS_FILE = "productos.txt";

    @FXML
    public void initialize() {
        // Crear algunos vendedores predefinidos
        Vendedor vendedor1 = new Vendedor("Juan", "Perez", "123456789", "Calle 123", "juanp", "1234");
        Vendedor vendedor2 = new Vendedor("Maria", "Gomez", "987654321", "Calle 456", "mariag", "5678");
        // Agregar los vendedores a la lista
        vendedores.agregarInicio(vendedor1);
        vendedores.agregarInicio(vendedor2);
        // Cargar vendedores y mensajes desde los archivos
        cargarVendedores();
        cargarMensajes();
        cargarProductos();
    }

    @FXML
    private void handleLogin() {
        String usuario = usuarioTextField.getText();
        String contrasena = contrasenaTextField.getText();

        for (Vendedor vendedor : vendedores) {
            if (vendedor.getUsuario().equals(usuario) && vendedor.getContrasena().equals(contrasena)) {
                vendedorActual = vendedor;
                vende.setText(vendedor.getNombre() + " " + vendedor.getApellidos());
                vende.setDisable(false);
                usuarioTextField.clear();
                contrasenaTextField.clear();
                usuarioTextField.setDisable(true);
                contrasenaTextField.setDisable(true);
                mostrarAlarma("Bienvenido " + vendedor.getNombre() + " !!", Alert.AlertType.CONFIRMATION);
                cerrar.setDisable(false);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ventas/estructuradatosfinal/VendedorView.fxml"));
                try {
                    Parent vendedorView = loader.load();
                    VendedorController vendedorController = loader.getController();
                    vendedorController.setVendedor(vendedor);

                    vende.setContent(vendedorView);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return;
            }
            if (vendedor.getUsuario().equals(usuario) && !vendedor.getContrasena().equals(contrasena)) {
                vendedorActual = vendedor;
                mostrarAlarma("Contraseña y/o Usuario incorrectos", Alert.AlertType.ERROR);
                return;
            }
        }
        System.out.println("Vendedor no encontrado");
    }

    @FXML
    private void cerrar() {
        vende.setText("Vendedor");
        mostrarAlarma("Sesión cerrada", Alert.AlertType.INFORMATION);
        vende.setDisable(true);
        cerrar.setDisable(true);
        usuarioTextField.clear();
        contrasenaTextField.clear();
        usuarioTextField.setDisable(false);
        contrasenaTextField.setDisable(false);
    }

    @FXML
    private void publicarMensaje() {
        String mensaje = mensajeTextField.getText();
        if (mensaje != null && !mensaje.isEmpty()) {
            if (vende.isDisable()) {
                muroListView.getItems().add(mensaje);
            } else {
                muroListView.getItems().add(vende.getText() + ": " + mensaje);
            }
            mensajeTextField.clear();
            guardarMensajes();
        }
    }

    @FXML
    private void crear() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ventas/estructuradatosfinal/RegistrarVendedor.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Registrar Vendedor");
            RegistroVendedorController controller = loader.getController();
            controller.setVendedores(vendedores);
            controller.setMainController(this);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void admins() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ventas/estructuradatosfinal/DashboardView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Administrador");
            DashboardController controller = loader.getController();


            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarVendedores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VENDEDORES_FILE))) {
            oos.writeInt(vendedores.getTamanio()); // Escribir el tamaño de la lista
            for (Vendedor vendedor : vendedores) {
                oos.writeObject(vendedor); // Escribir cada vendedor
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarVendedores() {
        File file = new File(VENDEDORES_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                int size = ois.readInt(); // Leer el tamaño de la lista
                for (int i = 0; i < size; i++) {
                    Vendedor vendedor = (Vendedor) ois.readObject(); // Leer cada vendedor
                    vendedores.agregarInicio(vendedor); // Agregarlo a la lista
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void guardarMensajes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String mensaje : muroListView.getItems()) {
                writer.write(mensaje);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarMensajes() {
        File file = new File(FILENAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String mensaje;
                while ((mensaje = reader.readLine()) != null) {
                    muroListView.getItems().add(mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void cargarProductos() {
        File file = new File(PRODUCTOS_FILE);
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


    public void mostrarAlarma(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alarma");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
