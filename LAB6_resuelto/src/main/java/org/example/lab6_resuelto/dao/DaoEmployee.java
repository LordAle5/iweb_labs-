package org.example.lab6_resuelto.dao;

import org.example.lab6_resuelto.beans.Employee;
import java.sql.*;
import java.util.ArrayList;

public class DaoEmployee {

    // ─────────────────────────────────────────
    // Listar todos los empleados y añadir en una lista
    // ─────────────────────────────────────────
    public ArrayList<Employee> listarEmployees() {
        ArrayList<Employee> lista = new ArrayList<>();
        try {
            Connection conn = Conexion.getConexion();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                Employee e = new Employee(); // Bean vacío

                e.setEmployeeId(rs.getInt("employee_id"));

                // CORRECCIÓN: Ahora se leen el nombre y apellido directamente a variables separadas.
                // Antes se concatenaban en una sola, lo cual causaba pérdida de información.
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));

                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("password"));
                e.setPhoneNumber(rs.getString("phone_number"));
                e.setHireDate(rs.getString("hire_date"));
                e.setJobId(rs.getString("job_id"));
                e.setSalary(rs.getDouble("salary"));
                e.setCommissionPct(rs.getDouble("commission_pct"));
                e.setManagerId(rs.getInt("manager_id"));
                e.setDepartmentId(rs.getInt("department_id"));
                e.setEnabled(rs.getInt("enabled"));

                lista.add(e);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // ─────────────────────────────────────────
    // Obtener un empleado por ID
    // ─────────────────────────────────────────
    public Employee obtenerEmployeePorId(int employeeId) {
        Employee e = null;
        try {
            Connection conn = Conexion.getConexion();

            String sql = "SELECT * FROM employees WHERE employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // si encontró el empleado
                e = new Employee();
                e.setEmployeeId(rs.getInt("employee_id"));

                // CORRECCIÓN: Ahora se leen el nombre y apellido directamente a variables separadas.
                // Antes se concatenaban en una sola, lo cual causaba pérdida de información.
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));

                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("password"));
                e.setPhoneNumber(rs.getString("phone_number"));
                e.setHireDate(rs.getString("hire_date"));
                e.setJobId(rs.getString("job_id"));
                e.setSalary(rs.getDouble("salary"));
                e.setCommissionPct(rs.getDouble("commission_pct"));
                e.setManagerId(rs.getInt("manager_id"));
                e.setDepartmentId(rs.getInt("department_id"));
                e.setEnabled(rs.getInt("enabled"));
            }
            conn.close();

        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        return e; // retorna null si no existe
    }

    // ─────────────────────────────────────────
    // Crear un empleado nuevo
    // ─────────────────────────────────────────
    public void crearEmployee(String firstName, String lastName,
                              String email, String password,
                              String phoneNumber, String hireDate,
                              String jobId, double salary,
                              int departmentId) {
        try {
            Connection conn = Conexion.getConexion();

            // Solo atributos obligatorios según la BD
            String sql = "INSERT INTO employees " +
                    "(first_name, last_name, email, password, " +
                    "phone_number, hire_date, job_id, salary, department_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, phoneNumber);
            ps.setString(6, hireDate);
            ps.setString(7, jobId);
            ps.setDouble(8, salary);
            ps.setInt(9, departmentId);
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────
    // Editar un empleado existente
    // ─────────────────────────────────────────
    public void editarEmployee(int employeeId, String firstName,
                               String lastName, String email,
                               String password, String phoneNumber,
                               String hireDate, String jobId,
                               double salary, int departmentId) {
        try {
            Connection conn = Conexion.getConexion();

            String sql = "UPDATE employees SET " +
                    "first_name = ?, last_name = ?, email = ?, " +
                    "password = ?, phone_number = ?, hire_date = ?, " +
                    "job_id = ?, salary = ?, department_id = ? " +
                    "WHERE employee_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(5, phoneNumber);
            ps.setString(6, hireDate);
            ps.setString(7, jobId);
            ps.setDouble(8, salary);
            ps.setInt(9, departmentId);
            ps.setInt(10, employeeId); // el WHERE
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────
    // Borrar un empleado por ID
    // ─────────────────────────────────────────
    public void borrarEmployee(int employeeId) {
        try {
            Connection conn = Conexion.getConexion();

            String sql = "DELETE FROM employees WHERE employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}