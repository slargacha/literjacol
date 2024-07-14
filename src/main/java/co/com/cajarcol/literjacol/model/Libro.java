package co.com.cajarcol.literjacol.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Generated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int numeroDeDescargas;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    @ElementCollection
    @Fetch(FetchMode.JOIN)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "libro_lenguaje", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "lenguaje")
    private List<Lenguaje> idiomas = new ArrayList<>();

    public Libro(){

    }

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
        this.idiomas = datosLibro.idiomas().stream()
                .map(Lenguaje::fromApi)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public void agregarAutor(Autor autor){
        this.autores.add(autor);
    }

    public List<Lenguaje> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Lenguaje> idiomas) {
        this.idiomas = idiomas;
    }

    public void agregarIdioma(Lenguaje idioma){
        this.idiomas.add(idioma);
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return String.format(
                "----- LIBRO -----\n" +
                        "Título: %s\n" +
                        "Autores: %s\n" +
                        "Idiomas: %s\n" +
                        "Número de descargas: %d\n" +
                        "----------------",
                this.titulo,
                this.autores.stream().map(Autor::getNombre).collect(Collectors.joining(", ")),
                this.idiomas.stream().map(Lenguaje::toString).collect(Collectors.joining(", ")),
                this.numeroDeDescargas
        );
    }
}
