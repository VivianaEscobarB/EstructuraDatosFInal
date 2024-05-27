package com.ventas.estructuradatosfinal.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlmacenamientoDatos {
    private Map<String, List<Producto>> productosPorVendedor;

    public AlmacenamientoDatos() {
        // Inicializa el mapa para almacenar productos por vendedor
        this.productosPorVendedor = new HashMap<>();
    }

    public void guardarProducto(String usuarioVendedor, Producto producto) {
        // Obtiene la lista de productos del vendedor o crea una nueva si no existe
        List<Producto> productos = productosPorVendedor.getOrDefault(usuarioVendedor, new ArrayList<>());
        productos.add(producto);
        productosPorVendedor.put(usuarioVendedor, productos);
    }

    public List<Producto> getProductosPorVendedor(String usuarioVendedor) {
        return productosPorVendedor.getOrDefault(usuarioVendedor, new ArrayList<>());
    }

}
