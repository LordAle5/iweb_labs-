package com.example.lab9_practicando.dao;

import com.example.lab9_practicando.beans.TicketTipo;
import com.example.lab9_practicando.dto.TicketDto;
import java.sql.*;
import java.util.ArrayList;

public class TicketDao extends DaoBase {

    // Listar tickets con info del evento (DTO)
    public ArrayList<TicketDto> listarTicketsDto() {
        ArrayList<TicketDto> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();

            String sql =
                    "SELECT tt.id_ticket_tipo, e.titulo, e.descripcion, " +
                            "e.fecha, e.lugar, tt.nombre, tt.precio, tt.cupo_disponible " +
                            "FROM ticket_tipo tt " +
                            "JOIN evento e ON tt.id_evento = e.id_evento";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                TicketDto dto = new TicketDto();
                dto.setIdTicketTipo(rs.getInt("id_ticket_tipo"));
                dto.setTituloEvento(rs.getString("titulo"));
                dto.setDescripcionEvento(rs.getString("descripcion"));
                dto.setFechaEvento(rs.getString("fecha"));
                dto.setLugarEvento(rs.getString("lugar"));
                dto.setNombreTicket(rs.getString("nombre"));
                dto.setPrecio(rs.getDouble("precio"));
                dto.setCupoDisponible(rs.getInt("cupo_disponible"));
                lista.add(dto);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Obtener un ticket por ID (para validar en reserva)
    public TicketTipo obtenerPorId(int idTicketTipo) {
        TicketTipo t = null;
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM ticket_tipo WHERE id_ticket_tipo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTicketTipo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new TicketTipo();
                t.setIdTicketTipo(rs.getInt("id_ticket_tipo"));
                t.setIdEvento(rs.getInt("id_evento"));
                t.setNombre(rs.getString("nombre"));
                t.setPrecio(rs.getDouble("precio"));
                t.setCupoTotal(rs.getInt("cupo_total"));
                t.setCupoDisponible(rs.getInt("cupo_disponible"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return t;
    }

    // Listar tickets de un evento (para el selector en Reserva)
    public ArrayList<TicketTipo> listarPorEvento(int idEvento) {
        ArrayList<TicketTipo> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM ticket_tipo WHERE id_evento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEvento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TicketTipo t = new TicketTipo();
                t.setIdTicketTipo(rs.getInt("id_ticket_tipo"));
                t.setIdEvento(rs.getInt("id_evento"));
                t.setNombre(rs.getString("nombre"));
                t.setPrecio(rs.getDouble("precio"));
                t.setCupoTotal(rs.getInt("cupo_total"));
                t.setCupoDisponible(rs.getInt("cupo_disponible"));
                lista.add(t);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Listar TODOS los tickets (para el selector general en formulario reserva)
    public ArrayList<TicketTipo> listarTodos() {
        ArrayList<TicketTipo> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM ticket_tipo");

            while (rs.next()) {
                TicketTipo t = new TicketTipo();
                t.setIdTicketTipo(rs.getInt("id_ticket_tipo"));
                t.setIdEvento(rs.getInt("id_evento"));
                t.setNombre(rs.getString("nombre"));
                t.setPrecio(rs.getDouble("precio"));
                t.setCupoTotal(rs.getInt("cupo_total"));
                t.setCupoDisponible(rs.getInt("cupo_disponible"));
                lista.add(t);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Crear tipo de ticket nuevo
    public void crearTicket(int idEvento, String nombre, double precio,
                            int cupoTotal, int cupoDisponible) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO ticket_tipo " +
                    "(id_evento, nombre, precio, cupo_total, cupo_disponible) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEvento);
            ps.setString(2, nombre);
            ps.setDouble(3, precio);
            ps.setInt(4, cupoTotal);
            ps.setInt(5, cupoDisponible);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Borrar tipo de ticket
    public void borrarTicket(int idTicketTipo) {
        try {
            Connection conn = getConnection();
            String sql = "DELETE FROM ticket_tipo WHERE id_ticket_tipo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idTicketTipo);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Disminuir cupo disponible al hacer una reserva
    public void disminuirCupo(int idTicketTipo, int cantidad) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE ticket_tipo SET cupo_disponible = cupo_disponible - ? " +
                    "WHERE id_ticket_tipo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, idTicketTipo);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Aumentar cupo disponible al cancelar una reserva
    public void aumentarCupo(int idTicketTipo, int cantidad) {
        try {
            Connection conn = getConnection();
            String sql = "UPDATE ticket_tipo SET cupo_disponible = cupo_disponible + ? " +
                    "WHERE id_ticket_tipo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, idTicketTipo);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}