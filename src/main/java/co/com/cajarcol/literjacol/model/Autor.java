package co.com.cajarcol.literjacol.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anoDeNacimiento;
    private Integer anoDeFallecimiento;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){

    }

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.anoDeNacimiento = datosAutor.anoDeNacimiento();
        this.anoDeFallecimiento = datosAutor.anoDeFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnoDeNacimiento() {
        return anoDeNacimiento;
    }

    public void setAnoDeNacimiento(int anoDeNacimiento) {
        this.anoDeNacimiento = anoDeNacimiento;
    }

    public int getAnoDeFallecimiento() {
        return anoDeFallecimiento;
    }

    public void setAnoDeFallecimiento(int anoDeFallecimiento) {
        this.anoDeFallecimiento = anoDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public void agregarLibro(Libro libro){
        this.libros.add(libro);
    }

    @Override
    public String toString() {
        return String.format(
                "Autor: %s\n" +
                "Fecha de nacimiento: %d\n" +
                "Fecha de fallecimiento: %d\n" +
                "Libros: %s\n",
                this.nombre,
                this.anoDeNacimiento,
                this.anoDeFallecimiento,
                this.libros.stream().map(Libro::getTitulo).collect(Collectors.joining(", "))
        );
    }
}
