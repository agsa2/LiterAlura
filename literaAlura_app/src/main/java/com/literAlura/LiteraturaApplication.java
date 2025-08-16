package com.literAlura;

import com.literAlura.principal.Principal;
import com.literAlura.repository.AutorRepository;
import com.literAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepositorio;
    @Autowired
    private AutorRepository autorRepositorio;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(livroRepositorio, autorRepositorio);
        principal.exibeMenu();
    }
}