package com.litenotes.controller;

import com.litenotes.model.Category;
import com.litenotes.model.Note;
import com.litenotes.service.CategoryService;
import com.litenotes.service.NoteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controlador principal de la aplicación LiteNotes. Gestiona la pantalla inicial,
 * permitiendo al usuario visualizar, filtrar, crear, editar y eliminar notas.
 * 
 * <p>Este controlador interactúa con {@link NoteService} y {@link CategoryService}
 * para obtener y manipular los datos mostrados en la interfaz.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class MainController {

    @FXML
    private ListView<Note> notesList;

    @FXML
    private ComboBox<Category> filterBox;

    private final NoteService noteService = new NoteService();
    private final CategoryService categoryService = new CategoryService();

    private final ObservableList<Note> notes = FXCollections.observableArrayList();

    /**
     * Inicializa la interfaz principal. Carga las notas, las categorías,
     * configura el filtro y habilita la apertura de notas mediante doble clic.
     */
    @FXML
    public void initialize() {

        // Cargar notas
        notes.setAll(noteService.getAllNotes());
        notesList.setItems(notes);

        // Cargar categorías en el filtro
        filterBox.getItems().add(new Category(0, "Todas"));
        filterBox.getItems().addAll(categoryService.getAllCategories());
        filterBox.getSelectionModel().selectFirst();

        filterBox.setOnAction(e -> applyFilter());

        // Doble clic para abrir nota
        notesList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Note selected = notesList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    openEditWindow(selected);
                }
            }
        });
    }

    /**
     * Aplica el filtro seleccionado en el ComboBox de categorías.
     * Si se selecciona "Todas", se muestran todas las notas.
     * En caso contrario, solo las notas pertenecientes a la categoría elegida.
     */
    private void applyFilter() {
        Category selected = filterBox.getValue();

        if (selected.getId() == 0) {
            notes.setAll(noteService.getAllNotes());
        } else {
            notes.setAll(noteService.getNotesByCategory(selected.getId()));
        }
    }

    /**
     * Abre la ventana de creación de una nueva nota.
     * Tras cerrarse la ventana, se actualiza la lista aplicando el filtro actual.
     */
    @FXML
    private void onCreateNote() {
        openWindow("/views/note-form.fxml");
        applyFilter();
    }

    /**
     * Elimina la nota seleccionada en la lista, si existe.
     * Tras eliminarla, se actualiza la lista aplicando el filtro actual.
     */
    @FXML
    private void onDeleteNote() {
        Note selected = notesList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            noteService.deleteNote(selected.getId());
            applyFilter();
        }
    }

    /**
     * Abre una ventana modal basada en un archivo FXML.
     *
     * @param fxml Ruta del archivo FXML que se desea cargar.
     * @throws RuntimeException si ocurre un error al cargar la vista.
     */
    private void openWindow(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo abrir la ventana: " + fxml, e);
        }
    }

    /**
     * Abre la ventana de edición de una nota existente.
     * Se pasa la nota seleccionada al controlador correspondiente.
     *
     * @param note Nota que se desea editar.
     * @throws RuntimeException si ocurre un error al cargar la vista de edición.
     */
    private void openEditWindow(Note note) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/note-edit.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            // Pasar la nota al controlador
            NoteEditController controller = loader.getController();
            controller.setNote(note);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar nota");
            stage.showAndWait();

            applyFilter(); // refrescar lista después de editar

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo abrir la ventana de edición.", e);
        }
    }
}
