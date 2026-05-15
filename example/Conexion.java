package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL  = "jdbc:mysql://localhost:3306/gimnasio_db?serverTimezone=America/Lima";
    private static final String USER = "root";
    private static final String PASS = "valar575";

    public static Connection getConexion() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }
        return conn;
    }
}