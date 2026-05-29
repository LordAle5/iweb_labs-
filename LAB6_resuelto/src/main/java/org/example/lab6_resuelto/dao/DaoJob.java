package org.example.lab6_resuelto.dao;

import org.example.lab6_resuelto.beans.Job;
import java.sql.*;
import java.util.ArrayList;

public class DaoJob {

    // Listar todos los puestos de trabajo que esten disponibles

    public ArrayList<Job> listarJobs() {
        ArrayList<Job> lista = new ArrayList<>();
        try {
            Connection conn = Conexion.getConexion();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM jobs");

            while (rs.next()) {
                Job job = new Job(); // Bean vacío
                job.setJobId(rs.getString("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setMinSalary(rs.getInt("min_salary"));
                job.setMaxSalary(rs.getInt("max_salary"));
                lista.add(job); // agrega a la lista
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // ─────────────────────────────────────────
    // Obtener un job por su ID
    // ─────────────────────────────────────────
    public Job obtenerJobPorId(String jobId) {
        Job job = null;
        try {
            Connection conn = Conexion.getConexion();

            // Usamos PreparedStatement porque tiene parámetro (?)

            String sql = "SELECT * FROM jobs WHERE job_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, jobId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // si encontró el job
                job = new Job();
                job.setJobId(rs.getString("job_id"));
                job.setJobTitle(rs.getString("job_title"));
                job.setMinSalary(rs.getInt("min_salary"));
                job.setMaxSalary(rs.getInt("max_salary"));
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return job; // retorna null si no existe
    }

    // ─────────────────────────────────────────
    // Agregar un nuevo puesto de trabajo
    // ─────────────────────────────────────────
    public void crearJob(String jobId, String jobTitle,
                         int minSalary, int maxSalary) {
        try {
            Connection conn = Conexion.getConexion();

            String sql = "INSERT INTO jobs (job_id, job_title, min_salary, max_salary) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, jobId);
            ps.setString(2, jobTitle);
            ps.setInt(3, minSalary);
            ps.setInt(4, maxSalary);
            ps.executeUpdate(); // para INSERT usamos executeUpdate

            conn.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}