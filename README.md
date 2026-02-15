# LiteNotes

Aplicación de escritorio desarrollada en Java 17 con JavaFX y SQLite, diseñada para la gestión sencilla y organizada de notas mediante categorías.

---

## Descripción

LiteNotes es una aplicación ligera que permite crear, editar, eliminar y clasificar notas de forma intuitiva.

Ha sido desarrollada siguiendo una arquitectura por capas para garantizar mantenibilidad, escalabilidad y una correcta separación de responsabilidades.

La aplicación utiliza:

- JavaFX para la interfaz gráfica
- SQLite como base de datos local
- JDBC para la conexión con la base de datos

---

## Funcionalidades

- Crear notas
- Editar notas
- Eliminar notas
- Gestionar categorías
- Filtrar notas por categoría
- Persistencia de datos local mediante SQLite

---

## Arquitectura del Proyecto

El proyecto sigue una arquitectura por capas:

src/
│
├── controller/ → Controladores JavaFX
├── model/ → Entidades del dominio (Nota, Categoría)
├── repository/ → Acceso a datos (SQLite)
├── service/ → Lógica de negocio
└── App.java → Punto de entrada

resources/
├── fxml/ → Vistas (FXML)
├── css/ → Estilos
└── litenotes.db → Base de datos


Esta organización permite una clara separación entre:

- Presentación
- Lógica de negocio
- Persistencia de datos

---

## Tecnologías Utilizadas

- Java 17
- JavaFX
- SQLite 3
- SQLite JDBC Driver (org.xerial:sqlite-jdbc)
- Scene Builder
- Visual Studio Code

---

## Requisitos

### Requisitos de Hardware

- CPU 1 GHz o superior
- 2 GB de RAM (4 GB recomendado)
- 50 MB de espacio libre
- Resolución mínima 1280x720

### Requisitos de Software

- Windows 10 o superior, Linux o macOS
- JDK 17 o superior
- JavaFX SDK
- SQLite JDBC Driver

---

## Ejecución en Entorno de Desarrollo

1. Instalar JDK 17.
2. Configurar JavaFX en el proyecto.
3. Añadir la dependencia sqlite-jdbc.
4. Verificar que los archivos FXML estén en la carpeta resources.
5. Ejecutar la clase `App`.

---

## Base de Datos

La aplicación utiliza una base de datos local:

- Archivo: `litenotes.db`
- Motor: SQLite 3
- Creación automática si no existe

### Tablas principales

Categorías
- id (clave primaria)
- nombre

Notas
- id (clave primaria)
- titulo
- contenido
- categoria_id (clave foránea)

Existe una relación uno-a-muchos entre Categorías y Notas.

---

## Uso de la Aplicación

### Crear una nota
1. Pulsar “Crear nota”.
2. Completar los campos obligatorios.
3. Guardar.

### Editar una nota
1. Doble clic sobre la nota.
2. Modificar la información.
3. Guardar cambios.

### Eliminar una nota
1. Seleccionar la nota.
2. Pulsar “Eliminar”.
3. Confirmar la acción.

### Filtrar por categoría
1. Seleccionar una categoría en el desplegable.
2. La lista se actualizará automáticamente.

---

## Posibles Mejoras Futuras

- Sistema de autenticación de usuarios
- Exportación de notas en formato PDF o TXT
- Sincronización en la nube
- Sistema de logging estructurado
- Empaquetado multiplataforma con jpackage

---

## Autor

Aníbal Moreno Martín  
Proyecto académico – 2º DAM  
Desarrollo de Aplicaciones Multiplataforma

---

## Licencia

Proyecto académico sin fines comerciales.
