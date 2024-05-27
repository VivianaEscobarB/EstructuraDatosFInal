package com.ventas.estructuradatosfinal.Model;
import java.time.LocalDateTime;

public class Producto {
    private String nombre;
    private String imagen;
    private String categoria;
    private double precio;
    private String estado; // "vendido", "publicado", "cancelado"
    private LocalDateTime fechaPublicacion;

    public Producto(String nombre, double precio, LocalDateTime fechaPublicacion) {
        this.nombre = nombre;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = "publicado"; // Por defecto, un producto se publica cuando se crea
    }

    // Getters y setters
}
