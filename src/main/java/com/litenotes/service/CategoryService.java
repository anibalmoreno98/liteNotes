package com.litenotes.service;

import com.litenotes.model.Category;
import com.litenotes.repository.CategoryRepository;

import java.util.List;

/**
 * Servicio encargado de gestionar la lógica relacionada con las categorías
 * dentro de la aplicación LiteNotes. Actúa como intermediario entre los
 * controladores y el repositorio {@link CategoryRepository}, proporcionando
 * un punto centralizado para obtener las categorías almacenadas.
 *
 * <p>Este servicio forma parte de la capa de negocio y permite mantener
 * separada la lógica de acceso a datos de la lógica de presentación.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class CategoryService {

    private final CategoryRepository repo = new CategoryRepository();

    /**
     * Obtiene todas las categorías disponibles en la base de datos.
     *
     * <p>Este método delega la consulta en {@link CategoryRepository#getAll()},
     * devolviendo la lista completa de categorías ordenadas alfabéticamente.</p>
     *
     * @return Una lista con todas las categorías existentes.
     */
    public List<Category> getAllCategories() {
        return repo.getAll();
    }
}
