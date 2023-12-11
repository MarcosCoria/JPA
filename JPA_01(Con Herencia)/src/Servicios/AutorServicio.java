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
import Persistencia.AutorDAO;
import java.util.List;

/* @author G96xyFast */
public class AutorServicio {
     private EditorialServicio editorialServicio;
    private LibroServicio libroServicio;
    private final AutorDAO DAO;
    // Generar nuevo DAO
    public AutorServicio() {
        this.DAO = new AutorDAO();
    }
    // Imporar los otros servicios relacionados
    public void setServicios(EditorialServicio editorialServicio, LibroServicio libroServicio) {
        this.editorialServicio = editorialServicio;
        this.libroServicio = libroServicio;
    }

    // este método invoca el método guardar de la clase DAO padre para 
    // persistir un objeto Autor
    public Autor crearAutor(String nombre, boolean alta) {
        Autor autor = new Autor();
        try {
            // NO SETEAMOS EL ID PORQUE VIENE POR DEFECTO
            autor.setNombre(nombre);
            autor.setAlta(alta);
            DAO.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Autor buscarPorId(String id) {
        try {
            return DAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Autor buscarPorNombre(String nombre) {
        try { // Buscar por Nombre!!
            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorId(String id) {
        try {
            DAO.eliminar(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Autor> listarAutor() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
