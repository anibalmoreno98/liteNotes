package com.litenotes.repository;

import com.litenotes.model.Category;
import com.litenotes.model.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio encargado de gestionar el acceso a los datos de las notas
 * almacenadas en la base de datos SQLite. Proporciona operaciones CRUD
 * (crear, leer, actualizar y eliminar) sobre la tabla {@code notes}.
 *
 * <p>Esta clase forma parte de la capa de persistencia y es utilizada por
 * {@link com.litenotes.service.NoteService} para obtener y manipular los datos
 * que se muestran en la interfaz de usuario.</p>
 *
 * <p>Las conexiones se gestionan mediante la clase {@link Database}, que
 * centraliza el acceso a la base de datos.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class NoteRepository {

    /**
     * Obtiene todas las notas almacenadas en la base de datos, incluyendo
     * la información de su categoría asociada. Las notas se devuelven
     * ordenadas de forma descendente por su identificador.
     *
     * <p>Este método realiza una unión entre las tablas {@code notes} y
     * {@code categories} para construir objetos {@link Note} completos.</p>
     *
     * @return Una lista con todas las notas existentes. Si ocurre un error,
     *         se devuelve una lista vacía.
     */
    public List<Note> getAll() {
        List<Note> list = new ArrayList<>();

        String sql = """
            SELECT n.id, n.title, n.content, n.category_id,
                   c.name AS category_name
            FROM notes n
            LEFT JOIN categories c ON n.category_id = c.id
            ORDER BY n.id DESC
        """;

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));

                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );

                note.setCategory(category);

                list.add(note);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Obtiene todas las notas pertenecientes a una categoría específica.
     *
     * <p>Este método filtra las notas mediante el campo {@code category_id}
     * y devuelve únicamente aquellas que coinciden con el identificador
     * proporcionado.</p>
     *
     * @param categoryId Identificador de la categoría por la que se desea filtrar.
     * @return Una lista de notas pertenecientes a la categoría indicada.
     *         Si ocurre un error, se devuelve una lista vacía.
     */
    public List<Note> getByCategory(int categoryId) {
        List<Note> list = new ArrayList<>();

        String sql = """
            SELECT n.id, n.title, n.content, n.category_id,
                   c.name AS category_name
            FROM notes n
            LEFT JOIN categories c ON n.category_id = c.id
            WHERE n.category_id = ?
            ORDER BY n.id DESC
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Note note = new Note();
                note.setId(rs.getInt("id"));
                note.setTitle(rs.getString("title"));
                note.setContent(rs.getString("content"));

                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );

                note.setCategory(category);

                list.add(note);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Inserta una nueva nota en la base de datos.
     *
     * <p>Este método almacena el título, contenido y categoría de la nota
     * en la tabla {@code notes}. El identificador se genera automáticamente
     * por la base de datos.</p>
     *
     * @param note La nota que se desea insertar.
     */
    public void insert(Note note) {
        String sql = "INSERT INTO notes (title, content, category_id) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getContent());
            pstmt.setInt(3, note.getCategory().getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza una nota existente en la base de datos.
     *
     * <p>Este método modifica el título, contenido y categoría de la nota
     * cuyo identificador coincida con el proporcionado.</p>
     *
     * @param note La nota con los nuevos valores que se desean guardar.
     */
    public void update(Note note) {
        String sql = "UPDATE notes SET title = ?, content = ?, category_id = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getContent());
            pstmt.setInt(3, note.getCategory().getId());
            pstmt.setInt(4, note.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina una nota de la base de datos según su identificador.
     *
     * @param id Identificador de la nota que se desea eliminar.
     */
    public void delete(int id) {
        String sql = "DELETE FROM notes WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
