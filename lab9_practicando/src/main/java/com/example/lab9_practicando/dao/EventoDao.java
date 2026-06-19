package com.example.lab9_practicando.dao;

import com.example.lab9_practicando.beans.Evento;
import com.example.lab9_practicando.dto.EventoDto;
import java.sql.*;
import java.util.ArrayList;

public class EventoDao extends DaoBase {

    // Listar eventos como DTO
    public ArrayList<EventoDto> listarEventosDto() {
        ArrayList<EventoDto> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(
                    "SELECT id_evento, titulo, descripcion, fecha, lugar FROM evento");

            while (rs.next()) {
                EventoDto dto = new EventoDto();
                dto.setIdEvento(rs.getInt("id_evento"));
                dto.setTitulo(rs.getString("titulo"));
                dto.setDescripcion(rs.getString("descripcion"));
                dto.setFecha(rs.getString("fecha"));
                dto.setLugar(rs.getString("lugar"));
                lista.add(dto);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Listar eventos para el selector (ComboBox) de Ticket y Reserva
    // Usamos el Bean aquí porque es solo para construir el <select>
    public ArrayList<Evento> listarEventosParaSelector() {
        ArrayList<Evento> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(
                    "SELECT id_evento, titulo, fecha FROM evento");

            while (rs.next()) {
                Evento e = new Evento();
                e.setIdEvento(rs.getInt("id_evento"));
                e.setTitulo(rs.getString("titulo"));
                e.setFecha(rs.getString("fecha"));
                lista.add(e);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // Crear evento nuevo
    public void crearEvento(String titulo, String descripcion,
                            String fecha, String lugar) {
        try {
            Connection conn = getConnection();
            String sql = "INSERT INTO evento (titulo, descripcion, fecha, lugar) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, descripcion);
            ps.setString(3, fecha);
            ps.setString(4, lugar);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Borrar evento
    public void borrarEvento(int idEvento) {
        try {
            Connection conn = getConnection();
            String sql = "DELETE FROM evento WHERE id_evento = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEvento);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}