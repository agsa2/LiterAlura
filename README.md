# LiterAlura - Catálogo de Livros

---

## 📖 Sobre o Projeto

**LiterAlura** é uma aplicação de console desenvolvida em Java com Spring Boot que funciona como um catálogo de livros interativo. O projeto consome a API pública [Gutendex](https://gutendex.com/) para buscar informações sobre livros e autores, permitindo ao usuário salvar esses dados em um banco de dados PostgreSQL para consultas futuras.

Esta aplicação foi criada como um desafio para praticar conceitos de desenvolvimento back-end, incluindo consumo de APIs, persistência de dados com Spring Data JPA e criação de uma aplicação interativa no terminal.

---

## ✨ Funcionalidades

O menu principal oferece as seguintes opções:

1.  **Buscar livro por título:** Consulta a API Gutendex, exibe o primeiro resultado encontrado e o salva no banco de dados, incluindo seu autor.
2.  **Listar todos os livros registrados:** Exibe uma lista de todos os livros que já foram salvos no banco de dados.
3.  **Listar todos os autores registrados:** Mostra uma lista com todos os autores salvos.
4.  **Listar autores vivos em um determinado ano:** Pede ao usuário um ano e exibe uma lista de autores que estavam vivos naquele período.
5.  **Listar livros em um determinado idioma:** Permite ao usuário buscar e listar todos os livros registrados em um idioma específico (ex: `en`, `pt`, `es`).

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot:** Framework principal para a criação da aplicação.
* **Spring Data JPA:** Para persistência de dados e comunicação com o banco.
* **PostgreSQL:** Banco de dados relacional para armazenar os dados.
* **Jackson Databind:** Biblioteca para converter a resposta JSON da API em objetos Java.
* **Maven:** Gerenciador de dependências do projeto.

---

## 🚀 Como Executar o Projeto

Siga os passos abaixo para rodar a aplicação localmente.

### Pré-requisitos

* **Java Development Kit (JDK)** - Versão 17 ou superior.
* **PostgreSQL** instalado e em execução.
* Um gerenciador de pacotes como **Maven** ou **Gradle**.
* Uma IDE de sua preferência, como IntelliJ IDEA ou VS Code.

### Passos

1.  **Crie o Banco de Dados:**
    * Abra o seu cliente PostgreSQL (como o `psql` ou DBeaver).
    * Crie um novo banco de dados chamado `literalura_db`.
        ```sql
        CREATE DATABASE literalura_db;
        ```

2.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/literAlura.git](https://github.com/seu-usuario/literAlura.git)
    cd literAlura
    ```

3.  **Configure a Conexão com o Banco:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as seguintes linhas com suas credenciais do PostgreSQL:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost/literalura_db
        spring.datasource.username=seu_usuario
        spring.datasource.password=sua_senha
        ```

4.  **Execute a Aplicação:**
    * Abra o terminal na raiz do projeto.
    * Execute o seguinte comando Maven para iniciar a aplicação:
        ```bash
        mvn spring-boot:run
        ```
    * Após a inicialização, o menu interativo será exibido no console.

---

## 🌐 API Utilizada

* **Gutendex:** Uma API gratuita para dados de livros do Projeto Gutenberg. Para mais informações, acesse [gutendex.com](https://gutendex.com/).
