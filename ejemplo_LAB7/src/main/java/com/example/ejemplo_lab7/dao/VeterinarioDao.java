package com.example.ejemplo_lab7.dao;


import com.example.ejemplo_lab7.beans.Veterinario;
import java.sql.*;
import java.util.ArrayList;

public class VeterinarioDao extends DaoBase {

    public ArrayList<Veterinario> listarVeterinarios() {
        ArrayList<Veterinario> lista = new ArrayList<>();
        try {
            Connection conn = getConnection(); // ← del DaoBase
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM veterinario");

            while (rs.next()) {
                Veterinario v = new Veterinario();
                v.setIdVeterinario(rs.getInt("idveterinario"));
                v.setNombre(rs.getString("nombre"));
                v.setEspecialidad(rs.getString("especialidad"));
                lista.add(v);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void crear() {}

    @Override
    public void borrar() {}
}