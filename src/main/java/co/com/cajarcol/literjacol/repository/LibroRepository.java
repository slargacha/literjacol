package co.com.cajarcol.literjacol.repository;

import co.com.cajarcol.literjacol.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
