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

import Entidad.Editorial;
import java.util.List;

/* @author G96xyFast */
public class EditorialDAO extends DAO <Editorial>{
     @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }

    public void eliminar(String id) throws Exception {
        Editorial editorial = buscarPorId(id);
        super.eliminar(editorial);
    }

    public Editorial buscarPorId(String id) throws Exception {
        conectar();
        Editorial editorial = em.find(Editorial.class, id);
        desconectar();
        return editorial;
    }

    public List<Editorial> listarTodos() throws Exception {
        conectar();
        List<Editorial> direcciones = em.createQuery("SELECT d FROM Editorial d")
                .getResultList();
        desconectar();
        return direcciones;
    }
}
