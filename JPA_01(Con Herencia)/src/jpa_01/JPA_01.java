/* Ejercicio_01: Sistema de Guardado de una Librería
Lo primero que se debe hacer es crear la base de datos sobre el que operará el sistema de 
reservas de libros. Para ello, se debe abrir el IDE de base de datos que se está utilizando 
(Workbench) y ejecutar la siguiente sentencia: 
CREATE DATABASE libreria; 
De esta manera se habrá creado una base de datos vacía llamada librería. 
Paquetes del Proyecto Java:
Los paquetes que se utilizarán para este proyecto son los siguientes: 
• entidades: en este paquete se almacenarán aquellas clases que se quiere persistir en la 
base de datos. 
• servicios: en este paquete se almacenarán aquellas clases que llevarán adelante la lógica 
del negocio. En general se crea un servicio para administrar las operaciones CRUD
(Create, Remove, Update, Delete) cada una de las entidades y las consultas de cada 
entidad. 

a) Entidades
Crearemos el siguiente modelo de entidades: 
Entidad Libro 
La entidad libro modela los libros que están disponibles en la biblioteca para ser prestados. En 
esta entidad, el atributo “ejemplares” contiene la cantidad total de ejemplares de ese libro, 
mientras que el atributo “ejemplaresPrestados” contiene cuántos de esos ejemplares se 
encuentran prestados en este momento y el atributo “ejemplaresRestantes” contiene cuántos 
de esos ejemplares quedan para prestar.
Entidad Autor 
La entidad autor modela los autores de libros. 
Entidad Editorial
La entidad editorial modela las editoriales que publican libros. 
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
Se debe seleccionar para este proyecto la librería “EclipseLink”. 
c) Servicios
AutorServicio 
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para 
administrar autores (consulta, creación, modificación y eliminación).
EditorialServicio 
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para 
administrar editoriales (consulta, creación, modificación y eliminación)
LibroServicio
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para 
administrar libros (consulta, creación, modificación y eliminación).
d) Main
Esta clase tiene la responsabilidad de llevar adelante las funcionalidades necesarias para 
interactuar con el usuario. En esta clase se muestra el menú de opciones con las operaciones 
disponibles que podrá realizar el usuario.
e) Tareas a realizar
Al alumno le toca desarrollar, las siguientes funcionalidades: 
1) Crear base de datos Librería
2) Crear unidad de persistencia
3) Crear entidades previamente mencionadas (excepto Préstamo) 
4) Generar las tablas con JPA
5) Crear servicios previamente mencionados.
6) Crear los métodos para persistir entidades en la base de datos librería
7) Crear los métodos para dar de alta/bajo o editar dichas entidades.
8) Búsqueda de un Autor por nombre.
9) Búsqueda de un libro por ISBN.
10) Búsqueda de un libro por Título. 
11) Búsqueda de un libro/s por nombre de Autor.
12) Búsqueda de un libro/s por nombre de Editorial.
13) Agregar las siguientes validaciones a todas las funcionalidades de la aplicación: 
• Validar campos obligatorios.
• No ingresar datos duplicados.  */
package jpa_01;

import Entidad.Autor;
import Entidad.Editorial;
import Entidad.Libro;
import Servicios.AutorServicio;
import Servicios.EditorialServicio;
import Servicios.LibroServicio;
import java.util.Scanner;

/* @author G96xyFast */
public class JPA_01 {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        AutorServicio as = new AutorServicio();
        EditorialServicio es = new EditorialServicio();
        LibroServicio ls = new LibroServicio();
        for (int i = 0; i < 2; i++) {
            System.out.println("DATOS LIBRO " + (i+1) + ":\nIngrese nombre del autor: ");
            String nombreAutor= leer.next();
            Autor autor= as.crearAutor(nombreAutor, true);
            
            System.out.println("Ingrese nombre de la editorial: ");
            String nombreEditorial= leer.next();
            Editorial editorial= es.crearEditorial(nombreEditorial, true);
            
            System.out.println("Ingrese nombre del Libro: ");
            String nombreLibro= leer.next();
            System.out.println("Ingreese año del libro: ");
            int anio= leer.nextInt();
            System.out.println("Ingrese total de ejemplares: ");
            int total = leer.nextInt();
            System.out.println("Total libros prestados: ");
            int totalP = leer.nextInt();
            System.out.println("Total libros restantes: ");
            int totalR = leer.nextInt();
            Libro libro= ls.crearLibro(nombreLibro, anio, total, totalP, totalR, true, autor, editorial);
        }
        
        try {
            boolean var = false;
            while (var == false) {
                System.out.println("MENÚ\n1-Buscar autor por nombre\n2-Buscar libro por ISBN\n3-Buscar libro por título\n4-Buscar libro por autor\n5-Buscar libro por editorial\n6-Salir");
                int num = leer.nextInt();
                switch (num) {
                    case 1:
                        System.out.println("Ingrese nombre autor: ");
                        String autor = leer.next();
                        Autor a = as.buscarPorNombre(autor);
                        System.out.println("Resultado: " + a);
                        break;
                    case 2:
                        System.out.println("Ingrese isbn: ");
                        long isbn = leer.nextLong();
                        Libro aux1 = ls.buscarPorIsbn(isbn);
                        System.out.println("Resultado: " + aux1);
                        break;
                    case 3:
                        System.out.println("Ingrese título: ");
                        String nombre = leer.next();
                        Libro aux2 = ls.buscarPorNombre(nombre);
                        System.out.println("Resultado: " + aux2);
                        break;
                    case 4:
                        System.out.println("Ingrese nombre del autor: ");
                        String nombreautor = leer.next();
                        Libro aux3 = ls.buscarPorAutor(nombreautor);
                        System.out.println("Resultado: " + aux3);
                        break;
                    case 5:
                        System.out.println("Ingrese nombre de la editorial: ");
                        String nombreEditorial = leer.next();
                        Libro aux4 = ls.buscarPorEditorial(nombreEditorial);
                        System.out.println("Resultado: " + aux4);
                        break;
                    case 6:
                        System.out.println("Finalizando programa.");
                        var = true;
                        break;
                    default:
                        System.out.println("Error. Ingrese una opción válida");
                }
            }
        } catch (Exception e) {
            System.out.println("Error de Sistema");
        }
    }
}