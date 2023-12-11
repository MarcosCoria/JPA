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

import Entidad.Editorial;
import Persistencia.EditorialDAO;
import java.util.List;

/* @author G96xyFast */
public class EditorialServicio {
      private AutorServicio autorServicio;
    private LibroServicio libroServicio;
    private final EditorialDAO DAO;
    // Generar nuevo DAO
    public EditorialServicio() {
        this.DAO = new EditorialDAO();
    }
    // Imporar los otros servicios relacionados
    public void setServicios(AutorServicio autorServicio, LibroServicio libroServicio) {
        this.autorServicio= autorServicio;
        this.libroServicio = libroServicio;
    }

    // este método invoca el método guardar de la clase DAO padre para 
    // persistir un objeto Editorial
    public Editorial crearEditorial(String nombre, boolean alta) {
        Editorial editorial = new Editorial();
        try {
            // NO SETEAMOS EL ID PORQUE VIENE POR DEFECTO
            editorial.setNombre(nombre);
            editorial.setAlta(alta);
            DAO.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Editorial buscarPorId(String id) {
        try {
            return DAO.buscarPorId(id);
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

    public List<Editorial> listarEditorial() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
