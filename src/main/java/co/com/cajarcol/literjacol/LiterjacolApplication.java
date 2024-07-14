package co.com.cajarcol.literjacol;

import co.com.cajarcol.literjacol.principal.Principal;
import co.com.cajarcol.literjacol.repository.AutorRepository;
import co.com.cajarcol.literjacol.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterjacolApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterjacolApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, autorRepository);
		principal.muestraElMenu();
	}
}
