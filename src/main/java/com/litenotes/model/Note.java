package com.litenotes.model;

/**
 * Representa una nota dentro de la aplicación LiteNotes. Cada nota contiene
 * un identificador único, un título, un contenido y una categoría asociada.
 * 
 * <p>Esta clase forma parte del modelo de datos y es utilizada por los
 * controladores y servicios para crear, editar, mostrar y almacenar notas
 * en la base de datos.</p>
 *
 * <p>Las notas pueden clasificarse mediante instancias de {@link Category},
 * lo que permite filtrarlas y organizarlas dentro de la aplicación.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class Note {

    private int id;
    private String title;
    private String content;
    private Category category;

    /**
     * Crea una instancia vacía de {@code Note}. Este constructor se utiliza
     * principalmente cuando se necesita inicializar la nota antes de asignarle
     * valores concretos.
     */
    public Note() {}

    /**
     * Crea una nueva nota con todos sus atributos especificados.
     *
     * @param id Identificador único de la nota.
     * @param title Título descriptivo de la nota.
     * @param content Contenido detallado de la nota.
     * @param category Categoría a la que pertenece la nota.
     */
    public Note(int id, String title, String content, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    /**
     * Crea una nueva nota sin especificar el identificador. Este constructor
     * se utiliza normalmente cuando la nota aún no ha sido almacenada en la
     * base de datos y el ID será asignado automáticamente.
     *
     * @param title Título descriptivo de la nota.
     * @param content Contenido detallado de la nota.
     * @param category Categoría a la que pertenece la nota.
     */
    public Note(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    /**
     * Devuelve el identificador único de la nota.
     *
     * @return El ID de la nota.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece un nuevo identificador para la nota.
     *
     * @param id Identificador que se asignará a la nota.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el título de la nota.
     *
     * @return El título actual de la nota.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece un nuevo título para la nota.
     *
     * @param title Nuevo título que se asignará a la nota.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Devuelve el contenido de la nota.
     *
     * @return El contenido actual de la nota.
     */
    public String getContent() {
        return content;
    }

    /**
     * Establece un nuevo contenido para la nota.
     *
     * @param content Texto que se asignará como contenido de la nota.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Devuelve la categoría asociada a la nota.
     *
     * @return La categoría actual de la nota.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Establece una nueva categoría para la nota.
     *
     * @param category Categoría que se asignará a la nota.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Devuelve el título de la nota como representación en texto. Esto permite
     * que la nota se muestre correctamente en elementos gráficos como ListView.
     *
     * @return El título de la nota.
     */
    @Override
    public String toString() {
        return title;
    }
}
