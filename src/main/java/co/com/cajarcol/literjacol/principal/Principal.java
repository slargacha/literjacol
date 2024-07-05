package co.com.cajarcol.literjacol.principal;

import co.com.cajarcol.literjacol.model.Autor;
import co.com.cajarcol.literjacol.model.DatosLibro;
import co.com.cajarcol.literjacol.model.Libro;
import co.com.cajarcol.literjacol.repository.LibroRepository;
import co.com.cajarcol.literjacol.service.ConsumoAPI;
import co.com.cajarcol.literjacol.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibro> datosLibro = new ArrayList<>();
    private LibroRepository repositorio;
    private List<Libro> libros;

    public Principal(LibroRepository repository){
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    //buscarEpisodioPorSerie();
                    break;
                case 3:
                    //mostrarSeriesBuscadas();
                    break;
                case 4:
                    //buscarSeriesPorTitulo();
                    break;
                case 5:
                    //buscarTop5Series();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        DatosLibro datos = conversor.obtenerDatos(json, DatosLibro.class);
        return datos;
    }

    public void buscarLibro() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);

        // Guardar autores primero

        // Guardar libro
        repositorio.save(libro);
        System.out.println("Libro y autores guardados exitosamente");
    }

    /*private void buscarLibro() {
        DatosLibro datos = getDatosLibro();
        Libro libro = new Libro(datos);
        repositorio.save(libro);
        //datosSeries.add(datos);
        System.out.println(datos);
    }*/

}
