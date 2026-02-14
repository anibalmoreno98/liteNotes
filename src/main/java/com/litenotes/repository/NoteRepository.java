package com.litenotes.repository;

import com.litenotes.model.Category;
import com.litenotes.model.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteRepository {

    // Obtener todas las notas con su categoría
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

    // Obtener notas filtradas por categoría
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

    // Crear nota
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


    // Actualizar nota
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

    // Eliminar nota
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
