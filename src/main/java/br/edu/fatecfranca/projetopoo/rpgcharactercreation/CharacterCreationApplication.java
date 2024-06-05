package br.edu.fatecfranca.projetopoo.rpgcharactercreation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CharacterCreationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CharacterCreationApplication.class, args);
	}

}
