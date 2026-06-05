package com.example.ejemplo_lab7.dao;

import com.example.ejemplo_lab7.beans.Dueno;
import java.sql.*;
import java.util.ArrayList;

public class DuenoDao extends DaoBase {

    public ArrayList<Dueno> listarDuenos() {
        ArrayList<Dueno> lista = new ArrayList<>();
        try {
            Connection conn = getConnection(); // ← del DaoBase
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM dueno");

            while (rs.next()) {
                Dueno d = new Dueno();
                d.setIdDueno(rs.getInt("iddueno"));
                d.setNombre(rs.getString("nombre"));
                d.setTelefono(rs.getString("telefono"));
                lista.add(d);
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