package com.ventas.estructuradatosfinal.Model;

import com.ventas.estructuradatosfinal.Listas.ListaSimple;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Vendedor implements Serializable{
    private String nombre;
    private String apellidos;
    private String cedula;
    private String direccion;
    private String usuario;
    private String contrasena;
    private ListaSimple<Producto> productos;
    private Set<Vendedor> contactos;
    private ListaSimple<Mensaje> muro;

    public Vendedor(String nombre, String apellidos, String cedula, String direccion, String usuario, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.direccion = direccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.productos = new ListaSimple<>();
        this.contactos = new HashSet<>();
        this.muro = new ListaSimple<>();
    }

    // Getters y setters

    public ListaSimple<Mensaje> getMuro() {
        return muro;
    }

    public void setMuro(ListaSimple<Mensaje> muro) {
        this.muro = muro;
    }

    public Set<Vendedor> getContactos() {
        return contactos;
    }

    public void setContactos(Set<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ListaSimple<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ListaSimple<Producto> productos) {
        this.productos = productos;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }



    public void agregarProducto(Producto producto) {
        productos.agregarInicio(producto);
    }
}
