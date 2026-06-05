package com.example.ejemplo_lab7.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    private String url  = "jdbc:mysql://localhost:3306/Veterinaria?serverTimezone=America/Lima";
    private String user = "root";
    private String pass = "valar575";


    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        return conn;
    }


    public abstract void crear();
    public abstract void borrar();
}