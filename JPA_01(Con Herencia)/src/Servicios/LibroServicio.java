/* c) Servicios
AutorServicio 
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para 
administrar autores (consulta, creación, modificación y eliminación).
EditorialServicio 
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para 
administrar editoriales (consulta, creación, modificación y eliminación)
LibroServicio
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para 
administrar libros (consulta, creación, modificación y eliminación) */
package Servicios;

import Entidad.Autor;
import Entidad.Editorial;
import Entidad.Libro;
import Persistencia.LibroDAO;
import java.util.List;

/* @author G96xyFast */
public class LibroServicio {
    private AutorServicio autorServicio;
    private LibroServicio LibroServicio;
    private final LibroDAO DAO;
    // Generar nuevo DAO
    public LibroServicio() {
        this.DAO = new LibroDAO();
    }
    // Imporar los otros servicios relacionados
    public void setServicios(AutorServicio autorServicio, LibroServicio LibroServicio) {
        this.autorServicio= autorServicio;
        this.LibroServicio = LibroServicio;
    }

    // este método invoca el método guardar de la clase DAO padre para 
    // persistir un objeto Libro
    public Libro crearLibro(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, boolean alta, Autor autor, Editorial editorial) {
        Libro libro = new Libro();
        try {
            // NO SETEAMOS EL ID PORQUE VIENE POR DEFECTO
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAlta(alta);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            DAO.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro buscarPorIsbn(long isbn) {
        try { //buscarPorId pertence a la clase DAO, por lo que éste útimo no lo vamos a modificar
            return DAO.buscarPorId(isbn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Libro buscarPorNombre(String nombre) {
        try { 
            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Libro buscarPorAutor(String nombre) {
        try { 
            return DAO.buscarPorAutor(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Libro buscarPorEditorial(String nombre) {
        try { 
            return DAO.buscarPorEditorial(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorIsbn(long isbn) {
        try {
            DAO.eliminar(isbn);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Libro> listarLibros() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}