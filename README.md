# Projeto Integrador - Desenvolvimento de Sistemas Orientado a Dispositivos Móveis e Baseados na Web

Aplicação focada em **organização de biblioteca pessoal** e **troca de livros entre usuários**, com uma experiência simples, segura e intuitiva. O objetivo é ampliar o acesso à leitura, incentivar a reutilização (sustentabilidade) e fortalecer a comunidade de leitores.

---

## Visão do Produto

O produto conecta pessoas interessadas em **trocar livros** de forma prática e eficiente, permitindo também **catalogar** e **organizar** a biblioteca pessoal.

### Problema que resolve
Muitas pessoas desejam ler mais, mas enfrentam:
- custo alto de livros físicos;
- dificuldade em encontrar trocas confiáveis;
- falta de organização da coleção pessoal;
- pouca clareza e segurança em negociações feitas em grupos de redes sociais.

### Benefícios
- economia na aquisição de novos livros (troca);
- incentivo à sustentabilidade por reutilização;
- expansão do repertório literário;
- organização da estante e controle do que está disponível para troca.

---

## Personas

### Persona I — Matheus Silva (19 anos)
**Perfil:** universitário/estagiário, orçamento apertado, fã de fantasia e ficção científica.  
**Objetivos:** ler mais sem gastar muito; encontrar pessoas com gostos parecidos.  
**Dores:** livros caros; frustração com lançamentos; falta de espaço confiável para troca.  
**Como o app ajuda:** troca livros já lidos por novos; filtros por gênero/sagas; recomendações e notificações.

### Persona II — Juliana Cristina
**Perfil:** leitora de livros físicos e e-books, rotina corrida, grande apego à estante.  
**Objetivos:** organizar a coleção; trocar poucos livros específicos; gerir o que tem e o que leu.  
**Dores:** pouco tempo; plataformas desorganizadas; dificuldade em separar “coleção” vs “troca”.  
**Como o app ajuda:** biblioteca digital organizada, marcação “para troca”/“coleção”, cadastro rápido e filtros.

### Persona III — Ricardo Menezes (32 anos)
**Perfil:** profissional de TI, pouco espaço em casa, fã de suspense/terror/thrillers.  
**Objetivos:** liberar espaço; trocar com segurança; encontrar leitores próximos com gostos parecidos.  
**Dores:** trocas confusas em redes sociais; pouca confiança; dificuldade de encontrar matches.  
**Como o app ajuda:** catálogo claro, filtros por gênero/localização, mensagens internas e confirmações.

---

## Jornada do Usuário (Resumo)

### Matheus — Limitação financeira
- **Gatilho:** indicação de amigo (“organizado e confiável”)
- **Momento de valor:** encontra troca por um livro de uma saga desejada
- **Resultado:** passa a usar o app como principal forma de conseguir livros

### Juliana — Organização e controle
- **Gatilho:** conteúdo sobre organização pessoal
- **Momento de valor:** consegue separar “coleção” de “para troca” e ter controle da estante
- **Resultado:** organiza a biblioteca e troca apenas títulos selecionados

### Ricardo — Espaço e segurança
- **Gatilho:** anúncio destacando filtros por gênero/localização
- **Momento de valor:** sugestão de troca com leitor do mesmo bairro e gosto
- **Resultado:** evita acumular e mantém apenas edições especiais

---

## Funcionalidades (planejadas)

- Cadastro/login de usuários
- Biblioteca pessoal (catalogação)
- Marcação de livros:
  - **Coleção**
  - **Disponível para troca**
- Busca e filtros (gênero, autor, título, localização)
- Match/negociação de troca
- Chat/mensagens internas
- Confirmação de troca e histórico
- Avaliações (opcional)

---

# Book Exchange API

API REST desenvolvida em Spring Boot para gerenciamento de troca de livros entre usuários.

O sistema permite:

- cadastro de usuários
- cadastro de livros
- associação de livros a usuários
- definição de disponibilidade para troca
- busca de livros por título, autor ou gênero

---

# Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger (documentação da API)

---

# Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- Java JDK 21
- Maven
- PostgreSQL

---

# Configuração do Banco de Dados

Criar um banco chamado:

```
book_exchange
```

Configurar o arquivo:

```
src/main/resources/application.properties
```

Exemplo:

```properties
spring.application.name=bookexchange

spring.datasource.url=jdbc:postgresql://localhost:5432/book_exchange
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

spring.sql.init.mode=always
```

Caso deseje inserir dados de teste:

```sql
INSERT INTO generos (nome_genero) VALUES ('Suspense'), ('Fantasia');

INSERT INTO usuarios (nome, email, cidade)
VALUES ('Ricardo Menezes', 'ricardo@email.com', 'São Paulo');

INSERT INTO livros (titulo, autor, id_genero)
VALUES ('O Homem de Giz', 'C.J. Tudor', 1);
```

---

# Executando o projeto

No terminal, dentro da pasta do projeto:

```bash
mvn spring-boot:run
```

A API iniciará em:

```
http://localhost:8080
```

---

# Documentação da API

A documentação interativa pode ser acessada pelo Swagger:

```
http://localhost:8080/swagger-ui.html
```

Nela é possível testar todos os endpoints diretamente pelo navegador.

---

# Endpoints principais

## Livros

Listar todos os livros

```
GET /books
```

Buscar livros por título

```
GET /books?titulo=hobbit
```

Buscar livros por autor

```
GET /books?autor=tolkien
```

Buscar livros por gênero

```
GET /books?genre=2
```

Buscar combinando filtros

```
GET /books?titulo=hobbit&autor=tolkien
```

Criar livro

```
POST /books
```

Body de exemplo:

```json
{
  "titulo": "O Hobbit",
  "autor": "J.R.R. Tolkien",
  "genero": {
    "id": 2
  }
}
```

---

## Usuários

Listar usuários

```
GET /users
```

Criar usuário

```
POST /users
```

Body de exemplo:

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "cidade": "São Paulo"
}
```

---

## Estante do usuário

Listar estantes

```
GET /shelf
```

Criar registro na estante

```
POST /shelf
```

Exemplo:

```json
{
  "usuario": { "id": 1 },
  "livro": { "id": 1 },
  "disponibilidade": "para_troca",
  "estadoConservacao": "Ótimo estado"
}
```

---

# Observações

- O sistema ainda não possui autenticação de login
- O cadastro de usuários atualmente não exige senha
- O frontend da aplicação será desenvolvido separadamente (Angular)
