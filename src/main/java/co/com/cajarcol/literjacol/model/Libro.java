package co.com.cajarcol.literjacol.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private int numeroDeDescargas;

    @ManyToMany
    @JoinTable(name = "libro_autor", joinColumns = @JoinColumn(name = "libro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    @ElementCollection(targetClass = Lenguaje.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "libro_lenguaje", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "lenguaje")
    private List<Lenguaje> idiomas;

    public Libro(){

    }

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
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

    public List<Lenguaje> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Lenguaje> idiomas) {
        this.idiomas = idiomas;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", numeroDeDescargas=" + numeroDeDescargas +
                '}';
    }
}
