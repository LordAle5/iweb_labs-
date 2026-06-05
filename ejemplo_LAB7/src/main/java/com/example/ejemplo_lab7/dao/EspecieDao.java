package com.example.ejemplo_lab7.dao;

import com.example.ejemplo_lab7.beans.Especie;
import java.sql.*;
import java.util.ArrayList;

// ← extends DaoBase → hereda getConnection()
public class EspecieDao extends DaoBase {

    public ArrayList<Especie> listarEspecies() {
        ArrayList<Especie> lista = new ArrayList<>();
        try {
            // getConnection() viene del DaoBase, no se repite aquí
            Connection conn = getConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM especie");

            while (rs.next()) {
                Especie e = new Especie();
                e.setIdEspecie(rs.getInt("idespecie"));
                e.setNombre(rs.getString("nombre"));
                lista.add(e);
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