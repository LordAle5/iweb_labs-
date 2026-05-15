package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {
    public static void main(String[] args) {
        String user = "root";
        String pass = "valar575";

        // 2. La URL de conexión (incluimos la zona horaria para que no de error) [cite: 1131, 1143]
        // Formato: jdbc:mysql://servidor:puerto/nombre_base_datos?parametros
        String url = "jdbc:mysql://localhost:3306/gimnasio_db?serverTimezone=America/Lima";

        try {
            // 3. Registramos el Driver en la memoria [cite: 1121, 1125]
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 4. Intentamos conectar [cite: 1134]
            Connection conn = DriverManager.getConnection(url, user, pass);

            System.out.println("¡Conexión exitosa a FitControl!");

            // 5. Siempre cerramos la conexión al terminar [cite: 1237]
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el Driver de MySQL. Revisa tu pom.xml.");
        } catch (SQLException e) {
            System.out.println("Error de SQL: " + e.getMessage());
        }
    }
}
