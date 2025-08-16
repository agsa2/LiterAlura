package com.literAlura.repository;

import com.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeContainingIgnoreCase(String nome);

    // Derived Query para encontrar autores vivos em um determinado ano.
    // A lógica é: o ano de nascimento deve ser menor ou igual ao ano fornecido,
    // E o ano de falecimento deve ser maior ou igual ao ano fornecido.
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivosEmAno(Integer ano);
}
