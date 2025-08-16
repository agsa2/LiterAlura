package com.literAlura.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * Classe de serviço responsável por fazer requisições à API Gutendex.
 */
public class ConsultaGutendex {

    private static final String API_URL = "https://gutendex.com/books/?search=";

    /**
     * Busca um livro na API Gutendex pelo título.
     *
     * @param titulo O título do livro a ser buscado.
     * @return Uma String contendo o corpo da resposta JSON da API.
     * @throws RuntimeException Se ocorrer um erro na comunicação com a API.
     */
    public static String buscarLivro(String titulo) {
        // Cria um cliente HTTP que será usado para fazer a requisição.
        HttpClient client = HttpClient.newHttpClient();
        try {
            // Codifica o título para ser seguro para uso em uma URL (ex: substitui espaços por %20).
            String encodedTitle = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String url = API_URL + encodedTitle;

            // Constrói a requisição HTTP GET para a URL da API.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Envia a requisição e espera pela resposta, que é tratada como uma String.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica se a requisição foi bem-sucedida.
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                // Lança uma exceção se a API retornar um status de erro.
                throw new RuntimeException("Falha na busca: Status code " + response.statusCode());
            }
        } catch (Exception e) {
            // Lança uma exceção genérica para qualquer outro erro (ex: problema de conexão).
            throw new RuntimeException("Não foi possível realizar a busca na API Gutendex.", e);
        }
    }
}
