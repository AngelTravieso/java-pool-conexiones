package org.atravieso.java.jdbc;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {

        // url de conexión (serverTimeZone? opcional)
        String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=UTC";
        String username = "root"; // usuario
        String password = "root"; // password

        // Se ejecuta de forma automática, es un autoClose, sea buena o mala la operación
        try (
            // Conexión a la BD
            Connection conn = DriverManager.getConnection(url, username, password);

            // Statement, devuelve un cursor
            Statement stmt = conn.createStatement();

            // Consulta
            ResultSet result = stmt.executeQuery("SELECT * FROM productos");
            ) {

            // Iterar el cursor con un while
            while (result.next()) {
                // Puede ser nombre de columna o ID de tabla (posición - empieza en 1)
                System.out.print(result.getInt("ID"));
                System.out.print(" | ");
                System.out.print(result.getString("nombre"));
                System.out.print(" | ");
                System.out.print(result.getDouble("precio"));
                System.out.print(" | ");
                System.out.println(result.getDate("fecha_registro"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}