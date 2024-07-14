package co.com.cajarcol.literjacol.principal;

import co.com.cajarcol.literjacol.model.*;
import co.com.cajarcol.literjacol.repository.AutorRepository;
import co.com.cajarcol.literjacol.repository.LibroRepository;
import co.com.cajarcol.literjacol.service.ConsumoAPI;
import co.com.cajarcol.literjacol.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private DatosLibro datosLibro;
    private LibroRepository repositorio;
    private AutorRepository repositorioAutor;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.repositorioAutor = autorRepository;
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
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutoresBuscados();
                    break;
                case 4:
                    mostrarAutoresVivosPorAno();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
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
        DatosApi datosApi = conversor.obtenerDatos(json, DatosApi.class);

        Optional<DatosLibro> libroBuscado = datosApi.resultados()
                .stream().filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            datosLibro = libroBuscado.get();
            return datosLibro;
        } else {
            System.out.println("Libro no encontrado, intenta con otro titulo");
            return null;
        }
    }

    private List<DatosAutor> getDatosAutor(DatosLibro libro){
        if (libro != null){
            List<DatosAutor> autores = libro.autores().stream()
                    .map(DatosAutor::new)
                    .toList();
            return autores;
        } else {
            System.out.println("No hay autores para este libro");
            return null;
        }
    }

    public void buscarLibro() {

        DatosLibro datos = getDatosLibro();

        Libro libro = new Libro(datosLibro);

        List<DatosAutor> datosAutor = getDatosAutor(datos);

        List<Autor> autores = datosAutor.stream()
                .map(Autor::new)
                .toList();

        try {

            libro.setAutores(autores);

            repositorio.save(libro);

            for (Autor aut : autores){
                aut.agregarLibro(libro);
                repositorioAutor.save(aut);
            }

            System.out.println("Libro y autores guardados exitosamente");

            System.out.println(libro.toString());

        } catch (DataIntegrityViolationException e) {
            e.getMessage();
        }

    }

    public void mostrarLibrosBuscados(){
        libros = repositorio.findAll();

        libros.stream()
                .forEach(System.out::println);
    }

    public void mostrarAutoresBuscados(){
        autores = repositorioAutor.findAll();

        autores.stream()
                .forEach(System.out::println);
    }

    public void mostrarAutoresVivosPorAno(){
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        int ano = Integer.parseInt(teclado.nextLine());

        autores = repositorioAutor.listarAutoresVivosPorAno(ano);

        autores.stream()
                .forEach(System.out::println);
    }

    public  void mostrarLibrosPorIdioma(){
        var menuIdioma = """
                Ingrese el idioma para buscar los libros:
                es- español
                en- ingles
                fr- frances
                """;
        System.out.println(menuIdioma);

        var idiomaElegido = teclado.nextLine();

        Lenguaje idioma = null;

        switch (idiomaElegido) {
            case "es":
                idioma = Lenguaje.ESPANOL;
                break;
            case "en":
                idioma = Lenguaje.INGLES;
                break;
            case "fr":
                idioma = Lenguaje.FRANCIA;
                break;
            default:
                System.out.println("Idioma inválido");
                return; // Salir del método si el idioma es inválido
        }

        libros = repositorio.listarLibrosPorIdioma(idioma);

        libros.stream()
                .forEach(System.out::println);
    }
}