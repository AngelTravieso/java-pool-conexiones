package org.atravieso.java.jdbc;

import org.atravieso.java.jdbc.util.ConexionBD;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {

        try (
                // Conexión a la BD (clase personalizada
                Connection conn = ConexionBD.getInstance();

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