package com.litenotes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación LiteNotes. Extiende {@link Application}
 * y actúa como punto de entrada para la ejecución de la interfaz gráfica
 * desarrollada con JavaFX.
 *
 * <p>Esta clase se encarga de cargar la vista principal definida en el archivo
 * FXML {@code main-view.fxml} y de inicializar la ventana principal de la
 * aplicación.</p>
 *
 * <p>El método {@link #main(String[])} inicia la aplicación llamando a
 * {@link Application#launch(String...)}.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class App extends Application {

    /**
     * Método de inicio de la aplicación JavaFX. Carga la vista principal desde
     * el archivo FXML y configura la ventana inicial.
     *
     * @param stage Ventana principal proporcionada por el entorno JavaFX.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("LiteNotes");
        stage.show();
    }

    /**
     * Método principal que inicia la ejecución de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        launch();
    }
}
