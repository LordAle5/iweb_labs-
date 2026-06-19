package com.example.lab9_practicando.dao;
import java.sql.*;

public abstract class DaoBase {
    private String url  = "jdbc:mysql://localhost:3306/lab10_eventos?serverTimezone=America/Lima";
    private String user = "root";
    private String pass = "valar575"; // ← cambia esto

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
}