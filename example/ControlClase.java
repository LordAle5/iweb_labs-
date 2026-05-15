package org.example;

import java.sql.*;
import java.util.Scanner;

public class ControlClase {

    Scanner sc = new Scanner(System.in);

    // OPCIÓN 3: Registrar clase
    public void registrarClase(ControlEntrenador controlEntrenador) {
        System.out.println("=== Registrar Clase ===");

        System.out.print("Nombre de la clase: ");
        String nombre = sc.nextLine();

        // Validar ID entrenador numérico
        int idEntrenador = 0;
        while (true) {
            try {
                System.out.print("ID del entrenador : ");
                idEntrenador = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El ID debe ser un numero.");
            }
        }

        // Verificar que el entrenador exista
        if (!controlEntrenador.existeEntrenador(idEntrenador)) {
            System.out.println("No existe un entrenador con ese ID.");
            return;
        }

        // Validar cupos
        int cupos = 0;
        while (true) {
            try {
                System.out.print("Cupos disponibles : ");
                cupos = Integer.parseInt(sc.nextLine());
                if (cupos > 0) break;
                System.out.println("Los cupos deben ser mayores a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Los cupos deben ser un numero.");
            }
        }

        // Validar precio
        double precio = 0;
        while (true) {
            try {
                System.out.print("Precio por clase  : ");
                precio = Double.parseDouble(sc.nextLine());
                if (precio > 0) break;
                System.out.println("El precio debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("El precio debe ser un numero.");
            }
        }

        Connection conn = Conexion.getConexion();
        try {
            String sql = "INSERT INTO clases (nombre_clase, id_entrenador, cupos_disponibles, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, idEntrenador);
            ps.setInt(3, cupos);
            ps.setDouble(4, precio);
            ps.executeUpdate();

            System.out.println("Clase registrada correctamente.");
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // OPCIÓN 6: Buscar clases
    public void buscarClase() {
        System.out.println("=== Buscar Clase ===");
        System.out.print("Buscar por ID, nombre o especialidad (Enter para ver todas): ");
        String filtro = sc.nextLine().trim();

        Connection conn = Conexion.getConexion();
        try {
            String sql;
            PreparedStatement ps;

            if (filtro.isEmpty()) {
                sql = "SELECT c.*, e.nombre AS nom_entrenador, e.especialidad " +
                        "FROM clases c JOIN entrenadores e ON c.id_entrenador = e.id_entrenador";
                ps  = conn.prepareStatement(sql);
            } else {
                sql = "SELECT c.*, e.nombre AS nom_entrenador, e.especialidad " +
                        "FROM clases c JOIN entrenadores e ON c.id_entrenador = e.id_entrenador " +
                        "WHERE CAST(c.id_clase AS CHAR) = ? OR c.nombre_clase LIKE ? OR e.especialidad LIKE ?";
                ps  = conn.prepareStatement(sql);
                ps.setString(1, filtro);
                ps.setString(2, "%" + filtro + "%");
                ps.setString(3, "%" + filtro + "%");
            }

            ResultSet rs = ps.executeQuery();
            boolean encontrado = false;

            while (rs.next()) {
                encontrado = true;
                int idClase   = rs.getInt("id_clase");
                String nomCls = rs.getString("nombre_clase");
                String entren = rs.getString("nom_entrenador");
                String espec  = rs.getString("especialidad");
                int cupos     = rs.getInt("cupos_disponibles");
                double precio = rs.getDouble("precio");

                System.out.println("-----------------------------");
                System.out.println("ID Clase    : " + idClase);
                System.out.println("Nombre      : " + nomCls);
                System.out.println("Entrenador  : " + entren);
                System.out.println("Especialidad: " + espec);
                System.out.println("Cupos       : " + cupos);
                System.out.println("Precio      : S/. " + precio);

                // Mostrar alumnos inscritos en esta clase
                String sqlAlumnos = "SELECT a.nombre FROM inscripciones i " +
                        "JOIN alumnos a ON i.id_alumno = a.id_alumno " +
                        "WHERE i.id_clase = ?";
                PreparedStatement psAlumnos = conn.prepareStatement(sqlAlumnos);
                psAlumnos.setInt(1, idClase);
                ResultSet rsAlumnos = psAlumnos.executeQuery();

                boolean tieneAlumnos = false;
                while (rsAlumnos.next()) {
                    if (!tieneAlumnos) {
                        System.out.println("Alumnos inscritos:");
                        tieneAlumnos = true;
                    }
                    System.out.println("  - " + rsAlumnos.getString("nombre"));
                }
                if (!tieneAlumnos) System.out.println("Alumnos : Sin inscritos");
            }

            if (!encontrado) System.out.println("No se encontraron clases.");
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // Método auxiliar: verificar si clase existe
    public boolean existeClase(int idClase) {
        Connection conn = Conexion.getConexion();
        try {
            String sql = "SELECT COUNT(*) FROM clases WHERE id_clase = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idClase);
            ResultSet rs = ps.executeQuery();
            rs.next();
            boolean existe = rs.getInt(1) > 0;
            conn.close();
            return existe;
        } catch (SQLException e) {
            return false;
        }
    }
}