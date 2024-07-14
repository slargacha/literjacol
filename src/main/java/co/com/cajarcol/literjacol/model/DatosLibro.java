package co.com.cajarcol.literjacol.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Integer numeroDeDescargas,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> idiomas
) {

    public DatosLibro(String titulo, List<DatosAutor> autores, List<String> idiomas, Integer numeroDeDescargas){
        this(titulo, numeroDeDescargas, autores, idiomas);
    }

}
