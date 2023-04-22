package eniconnect.eniconnect;

import eniconnect.eniconnect.entities.Etudiant;
import eniconnect.eniconnect.repositories.EtudiantRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EniconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EniconnectApplication.class, args);

	}

}
