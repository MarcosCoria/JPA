/* CONECTADO A LA TABLA 'libreria01' MEDIANTE EL 'persistence.xml'
b) Unidad de Persistencia
Para configurar la unidad de persistencia del proyecto, se recomienda leer el Instructivo 
Unidad de Persistencia recuerde hacer click con el botón derecho sobre el proyecto y 
seleccionar nuevo. A continuación, se debe seleccionar la opción de Persistence Unit como se 
indica en la siguiente imagen. 
Base de Datos
Para este proyecto nos vamos a conectar a la base de datos Librería, que creamos 
previamente.
Generación de Tablas 
La estrategia de generación de tablas define lo que hará JPA en cada ejecución, si debe crear 
las tablas faltantes, si debe eliminar todas las tablas y volver a crearlas o no hacer nada. 
Recomendamos en este proyecto utilizar la opción: “Create” 
Librería de Persistencia 
Se debe seleccionar para este proyecto la librería “EclipseLink” */
package Persistencia;

import Entidad.Libro;
import java.util.List;

/* @author G96xyFast */
public class LibroDAO extends DAO <Libro>{
     @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    public void eliminar(long id) throws Exception {
        Libro libro = buscarPorId(id);
        super.eliminar(libro);
    }

    public Libro buscarPorId(long id) throws Exception {
        conectar();
        Libro libro = em.find(Libro.class, id);
        desconectar();
        return libro;
    }
    
    public Libro buscarPorNombre(String nombre) throws Exception {
        conectar();
        // Buscar libro por nombre
        Libro aux=  (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.titulo LIKE :nombre ")
                .setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return aux;
    }
    
    public Libro buscarPorAutor(String nombre) throws Exception {
        conectar();
        /*SELECT a FROM Libro a WHERE a.autor.nombre LIKE : "Homero"*/
        
        // Buscar libro por autor
        Libro aux=  (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.autor.nombre LIKE :nombre ")
                .setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return aux;
    }

    public Libro buscarPorEditorial(String nombre) throws Exception {
        conectar();
        /*SELECT a FROM Libro a WHERE a.autor.nombre LIKE : "Homero"*/
        
        // Buscar libro por Editorial
        Libro aux=  (Libro) em.createQuery("SELECT a FROM Libro a WHERE a.editorial.nombre LIKE :nombre ")
                .setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return aux;
    }
    
    public List<Libro> listarTodos() throws Exception {
        conectar();
        List<Libro> direcciones = em.createQuery("SELECT d FROM Libro d")
                .getResultList();
        desconectar();
        return direcciones;
    }
}
