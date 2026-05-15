package org.example;

import java.sql.*;
import java.util.Scanner;

public class ControlEntrenador {

    Scanner sc = new Scanner(System.in);

    // OPCIÓN 2: Registrar entrenador
    public void registrarEntrenador() {
        System.out.println("=== Registrar Entrenador ===");

        System.out.print("Nombre       : ");
        String nombre = sc.nextLine();

        System.out.print("Especialidad : ");
        String especialidad = sc.nextLine();

        // Validar que teléfono sea solo números (con excepción)
        String telefono = "";
        while (true) {
            try {
                System.out.print("Telefono     : ");
                telefono = sc.nextLine();
                Long.parseLong(telefono); // si tiene letras lanza excepción
                break;
            } catch (NumberFormatException e) {
                System.out.println("El telefono debe contener solo numeros.");
            }
        }

        Connection conn = Conexion.getConexion();
        try {
            String sql = "INSERT INTO entrenadores (nombre, especialidad, telefono) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, especialidad);
            ps.setString(3, telefono);
            ps.executeUpdate();

            System.out.println("Entrenador registrado correctamente.");
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Método auxiliar: verificar si entrenador existe
    public boolean existeEntrenador(int idEntrenador) {
        Connection conn = Conexion.getConexion();
        try {
            String sql = "SELECT COUNT(*) FROM entrenadores WHERE id_entrenador = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEntrenador);
            ResultSet rs = ps.executeQuery();
            rs.next();
            boolean existe = rs.getInt(1) > 0;
            conn.close();
            return existe;
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            return false;
        }
    }
}