package org.atravieso.java.jdbc.repository;

import org.atravieso.java.jdbc.models.Producto;
import org.atravieso.java.jdbc.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Producto> {

    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }

    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {

            while(rs.next()) {
                Producto producto = crearProducto(rs);

                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public Producto porId(Long id) {

        Producto producto = null;

        try(
                PreparedStatement stmt = getConnection()
                        .prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            stmt.setLong(1, id); // indice, valor

            ResultSet res = stmt.executeQuery();

            if(res.next()) {
                producto = crearProducto(res);
            }

            res.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    private static Producto crearProducto(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getLong("ID"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecio(rs.getInt("precio"));
        producto.setFechaRegistro(rs.getDate("fecha_registro"));
        return producto;
    }
}
