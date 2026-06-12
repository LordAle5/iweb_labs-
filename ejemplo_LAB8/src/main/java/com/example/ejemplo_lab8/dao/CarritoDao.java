package com.example.ejemplo_lab8.dao;

import com.example.ejemplo_lab8.dto.CarritoDto;
import java.sql.*;
import java.util.ArrayList;

public class CarritoDao extends DaoBase {

    // Listar carrito con DTO
    public ArrayList<CarritoDto> listarCarritoDto(int idUsuario) {
        ArrayList<CarritoDto> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();

            // JOIN para traer nombre de producto y usuario
            // subtotal calculado directamente en SQL
            String sql =
                    "SELECT ci.id_item, ci.id_producto, p.nombre AS nombre_producto, " +
                            "CONCAT(u.nombres, ' ', u.apellidos) AS nombre_usuario, " +
                            "p.precio AS precio_unit, ci.cantidad, " +
                            "(p.precio * ci.cantidad) AS subtotal " +
                            "FROM carrito_item ci " +
                            "JOIN producto p ON ci.id_producto = p.id_producto " +
                            "JOIN usuario u ON ci.id_usuario = u.id_usuario " +
                            "WHERE ci.id_usuario = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CarritoDto dto = new CarritoDto();
                dto.setIdItem(rs.getInt("id_item"));
                dto.setIdProducto(rs.getInt("id_producto"));
                dto.setNombreProducto(rs.getString("nombre_producto"));
                dto.setNombreUsuario(rs.getString("nombre_usuario"));
                dto.setPrecioUnit(rs.getDouble("precio_unit"));
                dto.setCantidad(rs.getInt("cantidad"));
                dto.setSubtotal(rs.getDouble("subtotal"));
                lista.add(dto);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Agregar producto al carrito (cantidad = 1 por defecto)
    // Si ya existe, suma 1 a la cantidad
    public void agregarAlCarrito(int idUsuario, int idProducto) {
        try {
            Connection conn = getConnection();

            // INSERT OR UPDATE → si ya existe el producto en el carrito
            // suma 1 a la cantidad en vez de insertar duplicado
            String sql =
                    "INSERT INTO carrito_item (id_usuario, id_producto, cantidad) " +
                            "VALUES (?, ?, 1) " +
                            "ON DUPLICATE KEY UPDATE cantidad = cantidad + 1";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}