package org.atravieso.java.jdbc;

import org.atravieso.java.jdbc.models.Producto;
import org.atravieso.java.jdbc.repository.ProductoRepositoryImpl;
import org.atravieso.java.jdbc.repository.Repository;
import org.atravieso.java.jdbc.util.ConexionBD;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {

        try (
             // Conexión a la BD (clase personalizada
             Connection conn = ConexionBD.getInstance()) {

            Repository<Producto> repository = new ProductoRepositoryImpl();

            repository.listar().forEach(producto -> System.out.println(producto.getNombre()));

        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
}