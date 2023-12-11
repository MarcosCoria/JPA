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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/* @author G96xyFast */
public class DAO <T>{ // En este caso nos permite interactuar directamente con el objeto
    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPA_01PU");
    protected EntityManager em = EMF.createEntityManager();
    
    // Este método nos permite conectar con la base de datos
    // se verifica si la conexión está realizada, en caso que
    // no esté realizada, se realiza en la línea 24.
    protected void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    // Este método nos permite desconectarnos de la base de datos
    // Se verifica si existe una conexión, y de ser el caso, se
    // cierra la conexión y se desconecta el programa con la base de datos.
    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }
    
    // este método nos permite persistir un objeto en la base de datos.
    // Toma como parámetro el objeto a persistir, vemos que es genérico, por
    // lo que puede aceptar cualquier tipo de objeto (Dirección, Mastoca, Persona)
    protected void guardar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.persist(objeto); // Guarda o persiste
        em.getTransaction().commit();
        desconectar();
    }
    
    // Este método nos permite modificar una tupla de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    protected void editar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.merge(objeto); // Modifica
        em.getTransaction().commit();
        desconectar();
    }
    
    // Este método nos permite eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    protected void eliminar(T objeto){
        conectar();
        em.getTransaction().begin();
        em.remove(objeto); // Elimina
        em.getTransaction().commit();
        desconectar();
    }

}
