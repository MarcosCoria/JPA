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

import Entidad.Autor;
import java.util.List;

/* @author G96xyFast */
public class AutorDAO extends DAO <Autor>{
    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }
    
    public void eliminar(String id) throws Exception {
        Autor autor = buscarPorId(id);
        super.eliminar(autor);
    }

    public Autor buscarPorId(String id) throws Exception {
        conectar();
        Autor autor = em.find(Autor.class, id);
        desconectar();
        return autor;
    }
    
     public Autor buscarPorNombre(String nombre) throws Exception {
        conectar(); // Buscar autores por nombre
        Autor au = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre ")
                .setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return au;
    }

    public List<Autor> listarTodos() throws Exception {
        conectar();
        List<Autor> lista = em.createQuery("SELECT d FROM Autor d")
                .getResultList();
        desconectar();
        return lista;
    }
}
