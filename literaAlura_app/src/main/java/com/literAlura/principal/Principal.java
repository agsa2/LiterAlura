package com.literAlura.principal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literAlura.model.Autor;
import com.literAlura.model.Livro;
import com.literAlura.repository.AutorRepository;
import com.literAlura.repository.LivroRepository;
import com.literAlura.service.ConsultaGutendex;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final Scanner in = new Scanner(System.in, "UTF-8");
    private final LivroRepository livroRepositorio;
    private final AutorRepository autorRepositorio;
    private final ObjectMapper mapper = new ObjectMapper();

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \n*** Catálogo de Livros - Literatura ***
                    
                    1 - Buscar livro por título
                    2 - Listar todos os livros registrados
                    3 - Listar todos os autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    """;

            System.out.println(menu);
            System.out.print("Escolha uma opção: ");
            opcao = in.nextInt();
            in.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroPorTitulo() {
        System.out.print("Digite o título do livro para busca: ");
        var titulo = in.nextLine();
        try {
            String jsonResponse = ConsultaGutendex.buscarLivro(titulo);

            JsonNode root = mapper.readTree(jsonResponse);
            JsonNode results = root.path("results");

            if (results.isEmpty()) {
                System.out.println("Nenhum livro encontrado com este título.");
                return;
            }

            JsonNode primeiroLivroNode = results.get(0);
            String livroTitulo = primeiroLivroNode.path("title").asText();
            String idioma = primeiroLivroNode.path("languages").get(0).asText();
            Integer downloads = primeiroLivroNode.path("download_count").asInt();

            JsonNode primeiroAutorNode = primeiroLivroNode.path("authors").get(0);
            String autorNome = primeiroAutorNode.path("name").asText();
            Integer anoNasc = primeiroAutorNode.path("birth_year").asInt();
            Integer anoFalec = primeiroAutorNode.path("death_year").asInt();

            Optional<Autor> autorExistente = autorRepositorio.findByNomeContainingIgnoreCase(autorNome);
            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                autor = new Autor(autorNome, anoNasc, anoFalec);
                autorRepositorio.save(autor);
            }

            Livro livro = new Livro(livroTitulo, idioma, downloads, autor);
            livroRepositorio.save(livro);

            System.out.println("Livro salvo com sucesso!");
            System.out.println(livro);

        } catch (Exception e) {
            System.out.println("Erro ao buscar ou salvar o livro: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepositorio.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            System.out.println("\n--- Livros Registrados ---");
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            System.out.println("\n--- Autores Registrados ---");
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosPorAno() {
        System.out.print("Digite o ano para verificar autores vivos: ");
        var ano = in.nextInt();
        in.nextLine();
        List<Autor> autores = autorRepositorio.findAutoresVivosEmAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado para o ano de " + ano);
        } else {
            System.out.println("\n--- Autores Vivos em " + ano + " ---");
            autores.forEach(System.out::println);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma para busca: \nen: Englês \npt: Português \nes: Espanhol \nfr: Frances ");
        var idioma = in.nextLine();
        List<Livro> livros = livroRepositorio.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + idioma + "'.");
        } else {
            System.out.println("\n--- Livros no Idioma '" + idioma + "' ---");
            livros.forEach(System.out::println);
        }
    }
}
