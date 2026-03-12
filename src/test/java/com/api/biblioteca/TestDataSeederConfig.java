package com.api.biblioteca;

import com.api.biblioteca.model.*;
import com.api.biblioteca.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TestDataSeederConfig implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final EditoraRepository editoraRepository;
    private final AutorRepository autorRepository;
    private final AlunoRepository alunoRepository;
    private final LivroRepository livroRepository;

    private static final int QUANTIDADE_DE_LIVROS = 1000;

    @Override
    public void run(String... args) throws Exception {

        if (livroRepository.count() > 0) {
            log.info("Semeador: O banco de dados já possui livros. Geração aleatória ignorada.");
            return;
        }

        log.info("Iniciando Semeador Massivo: Gerando as bases e {} livros...", QUANTIDADE_DE_LIVROS);

        List<Categoria> categorias = salvarCategorias();
        List<Editora> editoras = salvarEditoras();
        List<Autor> autores = salvarAutores();
        salvarAlunos();

        Random random = new Random();
        List<Livro> livrosParaSalvar = new ArrayList<>();

        String[] prefixos = {
                "O Segredo de",
                "A Jornada de",
                "Guia Definitivo sobre",
                "Introdução a",
                "Crônicas de",
                "Mistério em",
                "A Arte de",
                "Fundamentos de",
                "O Último Voo para",
                "Contos de"
        };
        String[] temas = {
                "Java",
                "Marte",
                "Liderança",
                "Magia",
                "Algoritmos",
                "Guerra e Paz",
                "Investimentos",
                "Física Quântica",
                "Design Patterns",
                "Dragões",
                "Inteligência Artificial",
                "Cibersegurança"};

        for (int i = 1; i <= QUANTIDADE_DE_LIVROS; i++) {

            Categoria catAleatoria = categorias.get(random.nextInt(categorias.size()));
            Editora edAleatoria = editoras.get(random.nextInt(editoras.size()));

            int qtdAutores = random.nextInt(3) + 1;
            List<Autor> livroAutores = new ArrayList<>();
            for (int j = 0; j < qtdAutores; j++) {
                Autor autorAleatorio = autores.get(random.nextInt(autores.size()));
                if (!livroAutores.contains(autorAleatorio)) {
                    livroAutores.add(autorAleatorio);
                }
            }

            String prefixo = prefixos[random.nextInt(prefixos.length)];
            String tema = temas[random.nextInt(temas.length)];
            String titulo = prefixo + " " + tema + " (Edição " + i + ")";

            int ano = 1950 + random.nextInt(75);
            String isbn = "978-0-" + (100000 + random.nextInt(900000)) + "-" + random.nextInt(10);

            livrosParaSalvar.add(new Livro(null, titulo, ano, isbn, catAleatoria, edAleatoria, livroAutores));
        }

        log.info("Salvando {} livros no banco de dados. Isso pode levar alguns segundos...", QUANTIDADE_DE_LIVROS);
        livroRepository.saveAll(livrosParaSalvar);

        log.info("Semeador Massivo concluído com sucesso!");
    }

    private List<Categoria> salvarCategorias() {
        return categoriaRepository.saveAll(Arrays.asList(
                new Categoria(null, "Ficção Científica", "Livros espaciais e futuristas."),
                new Categoria(null, "Fantasia", "Magia e mundos épicos."),
                new Categoria(null, "Tecnologia e TI", "Programação e afins."),
                new Categoria(null, "Romance", "Histórias de amor dramáticas."),
                new Categoria(null, "Biografia", "Histórias reais de pessoas.")
        ));
    }

    private List<Editora> salvarEditoras() {
        return editoraRepository.saveAll(Arrays.asList(
                new Editora(null, "Alta Books", "11.111.111/0001-11", "contato@altabooks.com", "11999", "0000", "RJ", "Rio", "Centro", "Rua A", "Brasil", "web"),
                new Editora(null, "Intrínseca", "22.222.222/0001-22", "contato@intrinseca.com", "11888", "0000", "RJ", "Rio", "Botafogo", "Rua B", "Brasil", "web"),
                new Editora(null, "Novatec", "33.333.333/0001-33", "contato@novatec.com", "11777", "0000", "SP", "São Paulo", "Vila", "Rua C", "Brasil", "web"),
                new Editora(null, "Sextante", "44.444.444/0001-44", "contato@sextante.com", "11666", "0000", "RJ", "Rio", "Copacabana", "Rua D", "Brasil", "web")
        ));
    }

    private List<Autor> salvarAutores() {
        List<Autor> lista = new ArrayList<>();
        String[] nomes = {"J.R.R. Tolkien", "Robert C. Martin", "Isaac Asimov", "Agatha Christie", "Stephen King", "J.K. Rowling", "George R.R. Martin", "Machado de Assis", "Clarice Lispector", "Rick Riordan", "Neil Gaiman", "Arthur Conan Doyle"};
        for (String nome : nomes) {
            lista.add(new Autor(null, nome, null, "Desconhecida", null, null, null));
        }
        return autorRepository.saveAll(lista);
    }

    private void salvarAlunos() {
        alunoRepository.saveAll(Arrays.asList(
                new Aluno(null, "Cléber Silva", "12345678901", "11977777777", "cleber@email.com", "01000", "SP", "São Paulo", "Rua 1", "Centro"),
                new Aluno(null, "Maria Oliveira", "10987654321", "11966666666", "maria@email.com", "02000", "RJ", "Rio de Janeiro", "Av 2", "Copacabana"),
                new Aluno(null, "João Sousa", "11122233344", "11955555555", "joao@email.com", "03000", "MG", "Belo Horizonte", "Praça 3", "Savassi")
        ));
    }
}