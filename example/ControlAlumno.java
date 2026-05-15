package org.example;

import java.sql.*;
import java.util.Scanner;

public class ControlAlumno {

    Scanner sc = new Scanner(System.in);

    // OPCIÓN 1: Registrar alumno
    public void registrarAlumno() {
        System.out.println("=== Registrar Alumno ===");

        System.out.print("Nombre : ");
        String nombre = sc.nextLine();

        System.out.print("DNI    : ");
        String dni = sc.nextLine();

        System.out.print("Correo : ");
        String correo = sc.nextLine();

        // Validar edad con excepción (como vimos en clase)
        int edad = 0;
        while (true) {
            try {
                System.out.print("Edad   : ");
                edad = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("La edad debe ser un numero.");
            }
        }

        Connection conn = Conexion.getConexion();
        try {
            // Verificar DNI duplicado con PreparedStatement
            String sqlCheck = "SELECT COUNT(*) FROM alumnos WHERE dni = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setString(1, dni);
            ResultSet rs = psCheck.executeQuery();
            rs.next(); // movemos el cursor a la primera fila
            if (rs.getInt(1) > 0) {
                System.out.println("Ya existe un alumno con ese DNI.");
                conn.close();
                return;
            }

            // INSERT - ID lo genera MySQL automáticamente
            String sql = "INSERT INTO alumnos (nombre, dni, correo, edad) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, dni);
            ps.setString(3, correo);
            ps.setInt(4, edad);
            ps.executeUpdate(); // para INSERT usamos executeUpdate

            System.out.println("Alumno registrado correctamente.");
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // OPCIÓN 5: Buscar alumno
    public void buscarAlumno() {
        System.out.println("=== Buscar Alumno ===");
        System.out.print("Buscar por ID o nombre (Enter para ver todos): ");
        String filtro = sc.nextLine().trim();

        Connection conn = Conexion.getConexion();
        try {
            String sql;
            PreparedStatement ps;

            if (filtro.isEmpty()) {
                // Sin filtro: traer todos
                sql = "SELECT * FROM alumnos";
                ps  = conn.prepareStatement(sql);
            } else {
                // Con filtro: buscar por ID o nombre con LIKE
                // Las wildcards van en el setString, no en el SQL (como vimos en clase)
                sql = "SELECT * FROM alumnos WHERE CAST(id_alumno AS CHAR) = ? OR nombre LIKE ?";
                ps  = conn.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, "%" + filtro + "%"); // wildcard en setString
            }

            ResultSet rs = ps.executeQuery();
            boolean encontrado = false;

            while (rs.next()) {
                encontrado = true;
                int id        = rs.getInt("id_alumno");
                String nom    = rs.getString("nombre");
                String dni    = rs.getString("dni");
                String correo = rs.getString("correo");
                int edadRs    = rs.getInt("edad");

                System.out.println("-----------------------------");
                System.out.println("ID     : " + id);
                System.out.println("Nombre : " + nom);
                System.out.println("DNI    : " + dni);
                System.out.println("Correo : " + correo);
                System.out.println("Edad   : " + edadRs);

                // Mostrar clases inscritas del alumno
                String sqlClases = "SELECT c.nombre_clase FROM inscripciones i " +
                        "JOIN clases c ON i.id_clase = c.id_clase " +
                        "WHERE i.id_alumno = ?";
                PreparedStatement psClases = conn.prepareStatement(sqlClases);
                psClases.setInt(1, id);
                ResultSet rsClases = psClases.executeQuery();

                boolean tieneClases = false;
                while (rsClases.next()) {
                    if (!tieneClases) {
                        System.out.println("Clases inscritas:");
                        tieneClases = true;
                    }
                    System.out.println("  - " + rsClases.getString("nombre_clase"));
                }
                if (!tieneClases) {
                    System.out.println("Clases : Sin inscripciones");
                }
            }

            if (!encontrado) System.out.println("No se encontraron alumnos.");
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Método auxiliar: verificar si alumno existe (usado en otras clases)
    public boolean existeAlumno(int idAlumno) {
        Connection conn = Conexion.getConexion();
        try {
            String sql = "SELECT COUNT(*) FROM alumnos WHERE id_alumno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
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