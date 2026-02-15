package com.litenotes.model;

/**
 * Representa una categoría dentro de la aplicación LiteNotes. Cada categoría
 * contiene un identificador único y un nombre descriptivo. Las categorías se
 * utilizan para clasificar y organizar las notas creadas por el usuario.
 *
 * <p>Esta clase forma parte del modelo de datos y es utilizada por los
 * controladores y servicios para asignar categorías a las notas y para
 * mostrar las opciones disponibles en la interfaz.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class Category {

    private int id;
    private String name;

    /**
     * Crea una instancia vacía de {@code Category}. Este constructor se utiliza
     * principalmente cuando se necesita inicializar la categoría antes de
     * asignarle valores concretos.
     */
    public Category() {}

    /**
     * Crea una nueva categoría con el identificador y nombre especificados.
     *
     * @param id Identificador único de la categoría.
     * @param name Nombre descriptivo de la categoría.
     */
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Devuelve el identificador único de la categoría.
     *
     * @return El identificador de la categoría.
     */
    public int getId() { return id; }

    /**
     * Establece un nuevo identificador para la categoría.
     *
     * @param id Identificador que se asignará a la categoría.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Devuelve el nombre descriptivo de la categoría.
     *
     * @return El nombre de la categoría.
     */
    public String getName() { return name; }

    /**
     * Establece un nuevo nombre para la categoría.
     *
     * @param name Nombre que se asignará a la categoría.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Devuelve el nombre de la categoría como representación en texto.
     * Esto permite que la categoría se muestre correctamente en elementos
     * gráficos como ComboBox o ListView.
     *
     * @return El nombre de la categoría.
     */
    @Override
    public String toString() {
        return name;
    }
}
