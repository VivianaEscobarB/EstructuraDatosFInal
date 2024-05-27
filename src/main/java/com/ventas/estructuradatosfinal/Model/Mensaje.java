package com.ventas.estructuradatosfinal.Model;
import com.ventas.estructuradatosfinal.Listas.ListaSimple;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mensaje {
    private String autor;
    private String contenido;
    private LocalDateTime fechaPublicacion;
    private ListaSimple<String> comentarios;
    private int meGusta;

    public Mensaje(String autor, String contenido, LocalDateTime fechaPublicacion) {
        this.autor = autor;
        this.contenido = contenido;
        this.fechaPublicacion = fechaPublicacion;
        this.comentarios = new ListaSimple<>();
        this.meGusta = 0;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public ListaSimple<String> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(String comentario) {
        this.comentarios.agregarInicio(comentario);
    }

    public int getMeGusta() {
        return meGusta;
    }

    public void darMeGusta() {
        this.meGusta++;
    }
}
