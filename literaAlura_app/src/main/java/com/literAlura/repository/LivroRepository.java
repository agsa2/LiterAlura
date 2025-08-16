package com.literAlura.repository;

import com.literAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Derived Query para encontrar livros por idioma
    List<Livro> findByIdioma(String idioma);
}
