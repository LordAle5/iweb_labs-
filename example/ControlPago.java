package org.example;

import java.sql.*;
import java.util.Scanner;

public class ControlPago {

    Scanner sc = new Scanner(System.in);

    // OPCIÓN 7: Registrar pago de membresía
    public void registrarPago(ControlAlumno controlAlumno) {
        System.out.println("=== Registrar Pago de Membresia ===");

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

        if (!controlAlumno.existeAlumno(idAlumno)) {
            System.out.println("No existe un alumno con ese ID.");
            return;
        }

        // Validar tipo de membresía
        String tipo = "";
        while (true) {
            System.out.println("Tipo de membresia:");
            System.out.println("  1. Mensual");
            System.out.println("  2. Trimestral");
            System.out.println("  3. Anual");
            System.out.print("Opcion: ");
            String opcion = sc.nextLine();
            if (opcion.equals("1")) { tipo = "Mensual";     break; }
            else if (opcion.equals("2")) { tipo = "Trimestral"; break; }
            else if (opcion.equals("3")) { tipo = "Anual";      break; }
            else System.out.println("Opcion invalida. Elige 1, 2 o 3.");
        }

        // Validar monto
        double monto = 0;
        while (true) {
            try {
                System.out.print("Monto pagado: ");
                monto = Double.parseDouble(sc.nextLine());
                if (monto > 0) break;
                System.out.println("El monto debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("El monto debe ser un numero.");
            }
        }

        Connection conn = Conexion.getConexion();
        try {
            String sql = "INSERT INTO pagos (id_alumno, tipo_membresia, monto, fecha_pago) VALUES (?, ?, ?, CURDATE())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setString(2, tipo);
            ps.setDouble(3, monto);
            ps.executeUpdate();

            System.out.println("Pago registrado correctamente.");
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}