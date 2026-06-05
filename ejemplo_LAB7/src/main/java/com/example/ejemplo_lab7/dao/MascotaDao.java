package com.example.ejemplo_lab7.dao;

import com.example.ejemplo_lab7.beans.*;
import java.sql.*;
import java.util.ArrayList;

// extends DaoBase → hereda getConnection() y debe implementar crear() y borrar()
public class MascotaDao extends DaoBase {

    // ─────────────────────────────────────────
    // Listar todas las mascotas con JOIN
    // Necesitamos JOIN para traer los nombres
    // en vez de los IDs de especie, vet y dueño
    // ─────────────────────────────────────────
    public ArrayList<Mascota> listarMascotas() {
        ArrayList<Mascota> lista = new ArrayList<>();
        try {
            Connection conn = getConnection(); // ← del DaoBase
            Statement stmt  = conn.createStatement();

            // JOIN para traer nombres en vez de IDs
            String sql = "SELECT m.idmascota, m.nombre, m.edad, m.peso, " +
                    "e.idespecie, e.nombre AS especie, " +
                    "v.idveterinario, v.nombre AS veterinario, v.especialidad, " +
                    "d.iddueno, d.nombre AS dueno, d.telefono " +
                    "FROM mascota m " +
                    "JOIN especie e ON m.especie_id = e.idespecie " +
                    "JOIN veterinario v ON m.veterinario_id = v.idveterinario " +
                    "JOIN dueno d ON m.dueno_id = d.iddueno";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Crear objetos relacionados (Bean dentro de Bean)
                Especie esp = new Especie();
                esp.setIdEspecie(rs.getInt("idespecie"));
                esp.setNombre(rs.getString("especie"));

                Veterinario vet = new Veterinario();
                vet.setIdVeterinario(rs.getInt("idveterinario"));
                vet.setNombre(rs.getString("veterinario"));
                vet.setEspecialidad(rs.getString("especialidad"));

                Dueno due = new Dueno();
                due.setIdDueno(rs.getInt("iddueno"));
                due.setNombre(rs.getString("dueno"));
                due.setTelefono(rs.getString("telefono"));

                // Crear mascota y asignarle los objetos
                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idmascota"));
                m.setNombre(rs.getString("nombre"));
                m.setEdad(rs.getInt("edad"));
                m.setPeso(rs.getDouble("peso"));
                m.setEspecie(esp);       // ← Bean dentro de Bean
                m.setVeterinario(vet);   // ← Bean dentro de Bean
                m.setDueno(due);         // ← Bean dentro de Bean

                lista.add(m);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // ─────────────────────────────────────────
    // Filtrar mascotas por especie (Pregunta 4)
    // ─────────────────────────────────────────
    public ArrayList<Mascota> listarPorEspecie(int idEspecie) {
        ArrayList<Mascota> lista = new ArrayList<>();
        try {
            Connection conn = getConnection();

            // Igual que listarMascotas pero con WHERE
            String sql = "SELECT m.idmascota, m.nombre, m.edad, m.peso, " +
                    "e.idespecie, e.nombre AS especie, " +
                    "v.idveterinario, v.nombre AS veterinario, v.especialidad, " +
                    "d.iddueno, d.nombre AS dueno, d.telefono " +
                    "FROM mascota m " +
                    "JOIN especie e ON m.especie_id = e.idespecie " +
                    "JOIN veterinario v ON m.veterinario_id = v.idveterinario " +
                    "JOIN dueno d ON m.dueno_id = d.iddueno " +
                    "WHERE m.especie_id = ?"; // ← filtro

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEspecie);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Especie esp = new Especie();
                esp.setIdEspecie(rs.getInt("idespecie"));
                esp.setNombre(rs.getString("especie"));

                Veterinario vet = new Veterinario();
                vet.setIdVeterinario(rs.getInt("idveterinario"));
                vet.setNombre(rs.getString("veterinario"));
                vet.setEspecialidad(rs.getString("especialidad"));

                Dueno due = new Dueno();
                due.setIdDueno(rs.getInt("iddueno"));
                due.setNombre(rs.getString("dueno"));
                due.setTelefono(rs.getString("telefono"));

                Mascota m = new Mascota();
                m.setIdMascota(rs.getInt("idmascota"));
                m.setNombre(rs.getString("nombre"));
                m.setEdad(rs.getInt("edad"));
                m.setPeso(rs.getDouble("peso"));
                m.setEspecie(esp);
                m.setVeterinario(vet);
                m.setDueno(due);

                lista.add(m);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    // ─────────────────────────────────────────
    // IMPLEMENTACIÓN del método abstracto crear()
    // Pregunta 3 → crear mascota nueva
    // ─────────────────────────────────────────
    @Override
    public void crear() {
        // Este método vacío satisface el contrato abstracto
        // Usamos el método con parámetros de abajo
    }

    // Método crear con parámetros reales
    public void crearMascota(String nombre, int edad, double peso,
                             int especieId, int veterinarioId, int duenoId) {
        try {
            Connection conn = getConnection(); // ← del DaoBase
            String sql = "INSERT INTO mascota " +
                    "(nombre, edad, peso, especie_id, veterinario_id, dueno_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.setDouble(3, peso);
            ps.setInt(4, especieId);
            ps.setInt(5, veterinarioId);
            ps.setInt(6, duenoId);
            ps.executeUpdate(); // INSERT → executeUpdate
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────
    // IMPLEMENTACIÓN del método abstracto borrar()
    // Pregunta 3 → borrar mascota
    // ─────────────────────────────────────────
    @Override
    public void borrar() {
        // Este método vacío satisface el contrato abstracto
        // Usamos el método con parámetros de abajo
    }

    // Método borrar con parámetros reales
    public void borrarMascota(int idMascota) {
        try {
            Connection conn = getConnection(); // ← del DaoBase
            String sql = "DELETE FROM mascota WHERE idmascota = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idMascota);
            ps.executeUpdate(); // DELETE → executeUpdate
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
    }
}