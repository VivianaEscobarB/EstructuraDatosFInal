package com.ventas.estructuradatosfinal.Controller;

import com.ventas.estructuradatosfinal.Model.Mensaje;
import com.ventas.estructuradatosfinal.Model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ChatController {
    private Vendedor currentUser;
    private Map<Vendedor, Queue<Mensaje>> mensajes = new HashMap<>();

    @FXML
    private ListView<Mensaje> mensajesListView;
    @FXML
    private TextField mensajeTextField;

    public void setCurrentUser(Vendedor currentUser) {
        this.currentUser = currentUser;
    }

    @FXML
    public void enviarMensaje() {
        if (currentUser == null) {
            // Manejar el caso en que no hay usuario actual seleccionado
            return;
        }
        String contenido = mensajeTextField.getText();
        if (!contenido.isEmpty()) {
            Vendedor receptor = obtenerReceptor(); // Método para obtener el receptor adecuado
            Mensaje mensaje = new Mensaje(currentUser.getUsuario(), contenido, LocalDateTime.now());
            mensajes.computeIfAbsent(receptor, k -> new LinkedList<>()).add(mensaje);
            actualizarMensajesListView(receptor);
            mensajeTextField.clear();
        }
    }

    private Vendedor obtenerReceptor() {
        // Aquí puedes implementar la lógica para obtener el receptor adecuado
        // Por ejemplo, puedes tener un campo receptor en la clase ChatController
        // o puedes obtenerlo de la interfaz de usuario
        // Por ahora, lo dejaremos como una implementación ficticia
        return currentUser; // Simplemente usamos el usuario actual como receptor por ahora
    }

    private void actualizarMensajesListView(Vendedor receptor) {
        mensajesListView.getItems().clear();
        Queue<Mensaje> colaMensajes = mensajes.getOrDefault(receptor, new LinkedList<>());
        mensajesListView.getItems().addAll(colaMensajes);
    }
}
