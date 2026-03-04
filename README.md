# Sistema de Gestão de Biblioteca (API RESTful)

Este projeto é um sistema profissional e completo de gestão para bibliotecas.  
O backend foi desenvolvido utilizando **Java 21** e **Spring Boot 3**, implementando as melhores práticas de mercado em termos de arquitetura, segurança e padrões de projeto.

---

## Funcionalidades

### Segurança Profissional (JWT)
Sistema de autenticação e autorização via **JSON Web Tokens (JWT)** com **Spring Security**.

### Auto-Seeder de Administrador
Geração automática do utilizador administrador padrão na primeira execução do sistema.

### Gestão de Acervo (CRUD Completo)
Rotas para gerir:
- Categorias
- Editoras
- Autores
- Livros

### Gestão de Utentes
Registo e controlo de Alunos/Utilizadores.

### Sistema de Empréstimos Avançado
- Registo de empréstimo de um ou múltiplos livros
- Devolução individualizada de livros
- Cálculo automático de multas baseado nos dias de atraso no momento da devolução

---

## ️ Tecnologias e Padrões Utilizados

### Backend
- Java 21
- Spring Boot 3.3.0
- Spring Security (Autenticação Stateless com JWT)
- Spring Data JPA & Hibernate
- MySQL 8.0
- Flyway (Versionamento e Migrações de Base de Dados)
- MapStruct
- Lombok
- Bean Validation
- Swagger / OpenAPI 3

---

##️ Arquitetura e Padrões

- Padrão **DTO (Data Transfer Object)** para isolar as entidades da base de dados
- **Global Exception Handler (@RestControllerAdvice)** para tratamento centralizado de erros
    - 400 Bad Request
    - 401 Unauthorized
    - 404 Not Found
    - 422 Unprocessable Entity
- Arquitetura em camadas:
    - Controller
    - Service
    - Repository
    - Mapper

---

## Como Executar o Projeto

### Pré-requisitos

- Java Development Kit (JDK) 21
- Apache Maven
- MySQL Server a correr na porta 3306

---

###️ Passo 1: Configurar a Base de Dados

Crie uma base de dados vazia no seu MySQL chamada **biblioteca**:

```sql
CREATE DATABASE biblioteca;
```

>  Nota: O utilizador e senha padrão no `application.properties` estão configurados como:
>
> ```
> spring.datasource.username=[SEU_USUARIO]
> spring.datasource.password=[SUA_SENHA]
> ```
>
> Ajuste se necessário.

---

### Passo 2: Correr a API (Backend)

Na raiz do projeto, execute:

```bash
mvn spring-boot:run
```

O **Flyway** encarregar-se-á de criar todas as tabelas automaticamente (`V1__Criar_tabelas_iniciais.sql`).

De seguida, o `AdminSeederConfig` irá gerar o utilizador administrador padrão.

---

### Passo 3: Aceder à Documentação Swagger

Com a aplicação a correr, aceda pelo navegador:

👉 http://localhost:8080/swagger-ui/index.html

---

## Credenciais Padrão

Para testar as rotas no Swagger (após gerar o JWT), utilize as credenciais padrão criadas na primeira execução do sistema:

```
Utilizador: admin
Palavra-passe: 123456
```

> Estes valores podem ser alterados via Variáveis de Ambiente ou no ficheiro `application.properties` antes da primeira execução.

---

## Testar o Fluxo de Negócio (Atrasos e Multas)

O sistema possui uma regra de negócio inteligente para multas. Para testar:

1. Registe um Autor, Categoria e Editora através do Swagger
2. Registe um Aluno
3. Registe um Livro
4. Efetue um Empréstimo definindo a `dataDevolucaoPrevista` para uma data no passado (ex: há 5 dias)
5. Efetue a Devolução do Livro

A API irá:
- Informar o número de dias de atraso
- Gerar automaticamente o valor da multa correspondente
- Gravar o registo na tabela `multa`

---

## Status do Projeto

- ✔️ API Funcional
- ✔️ Segurança Implementada
- ✔️ Regras de Negócio Validadas
- ✔️ Documentação Swagger Integrada

---

## Desenvolvido por:

**Clebson Oliveira Ribeiro**

*Desenvolvedor Fullstack | Java | Spring Boot*  
