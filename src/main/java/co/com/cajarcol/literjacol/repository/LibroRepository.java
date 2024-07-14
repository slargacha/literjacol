package co.com.cajarcol.literjacol.repository;

import co.com.cajarcol.literjacol.model.Lenguaje;
import co.com.cajarcol.literjacol.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE i = :idioma")
    List<Libro> listarLibrosPorIdioma(Lenguaje idioma);
}
