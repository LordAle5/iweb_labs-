package com.example.ejemplo_lab8.dao;

import com.example.ejemplo_lab8.beans.Producto;
import com.example.ejemplo_lab8.dto.ProductoDto;
import java.sql.*;
import java.util.ArrayList;

public class ProductoDao extends DaoBase {

    // Listar productos con DTO
    // stock calculado = stock - cantidad en carrito
    public ArrayList<ProductoDto> listarProductosDto() {
        ArrayList<ProductoDto> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();

            // JOIN con categoria para el nombre
            // LEFT JOIN con carrito para calcular stock real
            // COALESCE → si no hay nada en carrito, usa 0
            String sql =
                    "SELECT p.id_producto, p.nombre, c.nombre AS categoria, " +
                            "p.precio, " +
                            "(p.stock - COALESCE(SUM(ci.cantidad), 0)) AS stock_real " +
                            "FROM producto p " +
                            "JOIN categoria c ON p.id_categoria = c.id_categoria " +
                            "LEFT JOIN carrito_item ci ON p.id_producto = ci.id_producto " +
                            "GROUP BY p.id_producto, p.nombre, c.nombre, p.precio, p.stock";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductoDto dto = new ProductoDto();
                dto.setId(rs.getInt("id_producto"));
                dto.setNombre(rs.getString("nombre"));
                dto.setCategoriaNombre(rs.getString("categoria"));
                dto.setPrecio(rs.getDouble("precio"));
                dto.setStock(rs.getInt("stock_real"));
                lista.add(dto);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Listar categorías para el ComboBox del formulario
    public ArrayList<Producto> listarCategorias() {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(
                    "SELECT id_categoria, nombre FROM categoria");
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setNombre(rs.getString("nombre"));
                lista.add(p);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Crear nuevo producto
    public void crearProducto(int idCategoria, String nombre,
                              String descripcion, double precio,
                              int stock) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO producto " +
                    "(id_categoria, nombre, descripcion, precio, stock) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            ps.setString(2, nombre);
            ps.setString(3, descripcion);
            ps.setDouble(4, precio);
            ps.setInt(5, stock);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}