package com.example.lab9_practicando.dao;

import com.example.lab9_practicando.dto.ReservaDto;
import java.sql.*;
import java.util.ArrayList;

public class ReservaDao extends DaoBase {

    // Listar reservas como DTO
    public ArrayList<ReservaDto> listarReservasDto() {
        ArrayList<ReservaDto> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();

            String sql =
                    "SELECT ri.id_item, e.titulo, e.fecha, " +
                            "u.nombres, u.apellidos, u.email, " +
                            "tt.nombre AS nombre_ticket, ri.cantidad " +
                            "FROM reserva_item ri " +
                            "JOIN usuario u ON ri.id_usuario = u.id_usuario " +
                            "JOIN ticket_tipo tt ON ri.id_ticket_tipo = tt.id_ticket_tipo " +
                            "JOIN evento e ON tt.id_evento = e.id_evento";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ReservaDto dto = new ReservaDto();
                dto.setIdItem(rs.getInt("id_item"));
                dto.setTituloEvento(rs.getString("titulo"));
                dto.setFechaEvento(rs.getString("fecha"));
                dto.setNombres(rs.getString("nombres"));
                dto.setApellidos(rs.getString("apellidos"));
                dto.setEmail(rs.getString("email"));
                dto.setNombreTicket(rs.getString("nombre_ticket"));
                dto.setCantidad(rs.getInt("cantidad"));
                lista.add(dto);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Crear reserva nueva
    public void crearReserva(int idUsuario, int idTicketTipo, int cantidad) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO reserva_item " +
                    "(id_usuario, id_ticket_tipo, cantidad) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idTicketTipo);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Obtener cantidad e idTicketTipo de una reserva (para cancelar y devolver cupo)
    public int[] obtenerDatosParaCancelar(int idItem) {
        int[] datos = new int[2]; // [0]=idTicketTipo, [1]=cantidad
        try {
            Connection conn = getConnection();
            String sql = "SELECT id_ticket_tipo, cantidad FROM reserva_item " +
                    "WHERE id_item = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idItem);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                datos[0] = rs.getInt("id_ticket_tipo");
                datos[1] = rs.getInt("cantidad");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return datos;
    }

    // Borrar (cancelar) reserva
    public void borrarReserva(int idItem) {
        try {
            Connection conn = getConnection();
            String sql = "DELETE FROM reserva_item WHERE id_item = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idItem);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}