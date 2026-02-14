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

public class MainController {

    @FXML
    private ListView<Note> notesList;

    @FXML
    private ComboBox<Category> filterBox;

    private final NoteService noteService = new NoteService();
    private final CategoryService categoryService = new CategoryService();

    private final ObservableList<Note> notes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        notes.setAll(noteService.getAllNotes());
        notesList.setItems(notes);

        // Filtro de categorías
        filterBox.getItems().add(new Category(0, "Todas"));
        filterBox.getItems().addAll(categoryService.getAllCategories());
        filterBox.getSelectionModel().selectFirst();

        filterBox.setOnAction(e -> applyFilter());
    }

    private void applyFilter() {
        Category selected = filterBox.getValue();

        if (selected.getId() == 0) {
            notes.setAll(noteService.getAllNotes());
        } else {
            notes.setAll(noteService.getNotesByCategory(selected.getId()));
        }
    }

    @FXML
    private void onCreateNote() {
        openWindow("/views/note-form.fxml");
        applyFilter();
    }

    @FXML
    private void onDeleteNote() {
        Note selected = notesList.getSelectionModel().getSelectedItem();
        if (selected != null) {
            noteService.deleteNote(selected.getId());
            applyFilter();
        }
    }

    private void openWindow(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
