package com.ventas.estructuradatosfinal;

import com.ventas.estructuradatosfinal.Controller.MainController;
import com.ventas.estructuradatosfinal.Model.Vendedor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private MainController mainController;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/ventas/estructuradatosfinal/MainView.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador
            mainController = loader.getController();

            // Configurar la escena
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Aplicación de Ventas");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
