package com.litenotes.repository;

import com.litenotes.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a los datos de categorías
 * almacenadas en la base de datos SQLite. Proporciona métodos para consultar
 * las categorías disponibles y devolverlas como objetos {@link Category}.
 *
 * <p>Esta clase forma parte de la capa de persistencia y es utilizada por
 * {@link com.litenotes.service.CategoryService} para obtener la información
 * necesaria para la interfaz y la lógica de negocio.</p>
 *
 * <p>Las conexiones se gestionan mediante la clase {@code Database}, que
 * proporciona el acceso centralizado a la base de datos.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class CategoryRepository {

    /**
     * Obtiene todas las categorías almacenadas en la base de datos, ordenadas
     * alfabéticamente por nombre.
     *
     * <p>Este método ejecuta una consulta SQL sobre la tabla {@code categories}
     * y convierte cada fila del resultado en una instancia de {@link Category}.</p>
     *
     * @return Una lista con todas las categorías existentes. Si ocurre un error
     *         durante la consulta, se devuelve una lista vacía.
     */
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM categories ORDER BY name";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
