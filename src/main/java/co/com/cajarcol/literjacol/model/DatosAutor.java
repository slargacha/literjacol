package co.com.cajarcol.literjacol.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer anoDeNacimiento,
        @JsonAlias("death_year") Integer anoDeFallecimiento) {

    public DatosAutor(DatosAutor datosAutor) {
        this(datosAutor.nombre(), datosAutor.anoDeNacimiento, datosAutor.anoDeFallecimiento());
    }
}
