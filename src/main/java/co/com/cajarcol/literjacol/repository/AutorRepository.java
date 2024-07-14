package co.com.cajarcol.literjacol.repository;

import co.com.cajarcol.literjacol.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository <Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.anoDeNacimiento <= :ano AND a.anoDeFallecimiento >= :ano")
    List<Autor> listarAutoresVivosPorAno(int ano);

}
