package com.example.ejemplo_lab8.dao;

import com.example.ejemplo_lab8.beans.Usuario;
import java.sql.*;

public class UsuarioDao extends DaoBase {

    // Validar login con SHA2 (contraseña hasheada)
    // El hash ya está en la BD, comparamos con SHA2 del input
    public Usuario validarLogin(String email, String password) {
        Usuario u = null;
        try {
            Connection conn = getConnection();

            // SHA2(?, 256) → MySQL hashea el password que viene del form
            // y lo compara con el hash guardado en la BD
            String sql = "SELECT * FROM usuario " +
                    "WHERE email = ? " +
                    "AND password_hash = SHA2(?, 256) " +
                    "AND estado = 'ACTIVO'";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password); // ← texto plano, MySQL lo hashea
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombres(rs.getString("nombres"));
                u.setApellidos(rs.getString("apellidos"));
                u.setEmail(rs.getString("email"));
                u.setEstado(rs.getString("estado"));
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return u; // null si no encontró
    }
}