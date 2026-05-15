package org.example;

import java.sql.*;
import java.util.Scanner;

public class ControlInscripcion {

    Scanner sc = new Scanner(System.in);

    // OPCIÓN 4: Inscribir alumno a una clase
    public void inscribirAlumno(ControlAlumno controlAlumno, ControlClase controlClase) {
        System.out.println("=== Inscribir Alumno a Clase ===");

        // Validar ID alumno
        int idAlumno = 0;
        while (true) {
            try {
                System.out.print("ID del alumno: ");
                idAlumno = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El ID debe ser un numero.");
            }
        }

        // Validar ID clase
        int idClase = 0;
        while (true) {
            try {
                System.out.print("ID de la clase: ");
                idClase = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El ID debe ser un numero.");
            }
        }

        // Verificar existencia
        if (!controlAlumno.existeAlumno(idAlumno)) {
            System.out.println("No existe un alumno con ese ID.");
            return;
        }
        if (!controlClase.existeClase(idClase)) {
            System.out.println("No existe una clase con ese ID.");
            return;
        }

        Connection conn = Conexion.getConexion();
        try {
            // Verificar cupos disponibles
            String sqlCupos = "SELECT cupos_disponibles FROM clases WHERE id_clase = ?";
            PreparedStatement psCupos = conn.prepareStatement(sqlCupos);
            psCupos.setInt(1, idClase);
            ResultSet rsCupos = psCupos.executeQuery();
            rsCupos.next();
            if (rsCupos.getInt("cupos_disponibles") <= 0) {
                System.out.println("La clase no tiene cupos disponibles.");
                conn.close();
                return;
            }

            // Verificar que no esté inscrito ya
            String sqlDup = "SELECT COUNT(*) FROM inscripciones WHERE id_alumno = ? AND id_clase = ?";
            PreparedStatement psDup = conn.prepareStatement(sqlDup);
            psDup.setInt(1, idAlumno);
            psDup.setInt(2, idClase);
            ResultSet rsDup = psDup.executeQuery();
            rsDup.next();
            if (rsDup.getInt(1) > 0) {
                System.out.println("El alumno ya esta inscrito en esta clase.");
                conn.close();
                return;
            }

            // Insertar inscripción con fecha actual
            String sqlIns = "INSERT INTO inscripciones (id_alumno, id_clase, fecha_inscripcion) VALUES (?, ?, CURDATE())";
            PreparedStatement psIns = conn.prepareStatement(sqlIns);
            psIns.setInt(1, idAlumno);
            psIns.setInt(2, idClase);
            psIns.executeUpdate();

            // Disminuir cupo en 1
            String sqlUpd = "UPDATE clases SET cupos_disponibles = cupos_disponibles - 1 WHERE id_clase = ?";
            PreparedStatement psUpd = conn.prepareStatement(sqlUpd);
            psUpd.setInt(1, idClase);
            psUpd.executeUpdate();

            System.out.println("Inscripcion realizada correctamente.");
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}