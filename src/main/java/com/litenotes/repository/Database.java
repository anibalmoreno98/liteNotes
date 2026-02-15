package com.litenotes.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexión con la base de datos SQLite utilizada
 * por la aplicación LiteNotes. Proporciona un método centralizado para obtener
 * conexiones válidas mediante JDBC.
 *
 * <p>La base de datos utilizada es un archivo local llamado {@code litenotes.db},
 * ubicado en el directorio raíz de la aplicación. Esta clase es utilizada por
 * los repositorios para ejecutar consultas SQL.</p>
 *
 * <p>El uso de un método estático permite un acceso sencillo y uniforme a la
 * conexión desde cualquier parte del proyecto.</p>
 *
 * @author Aníbal
 * @version 1.0
 * @since 2026
 */
public class Database {

    /** URL de conexión JDBC para acceder a la base de datos SQLite. */
    private static final String URL = "jdbc:sqlite:litenotes.db";

    /**
     * Obtiene una conexión activa a la base de datos SQLite.
     *
     * <p>Este método utiliza {@link DriverManager#getConnection(String)} para
     * establecer la conexión. Si ocurre un error, se lanza una excepción
     * {@link SQLException} que debe ser gestionada por el repositorio que
     * realiza la operación.</p>
     *
     * @return Una conexión JDBC válida a la base de datos.
     * @throws SQLException Si no es posible establecer la conexión.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
