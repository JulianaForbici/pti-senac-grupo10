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

# Execução com Docker (dev)

Para rodar o **backend** e o **PostgreSQL** em contêineres, é necessário ter **Docker** e **Docker Compose** instalados.

## Subindo os serviços (backend + banco)

Na raiz do repositório, execute:

```bash
docker compose up --build
```

Isso irá:

- criar um contêiner `db` com PostgreSQL (banco `book_exchange`, usuário `postgres`, senha `postgres`);
- criar e subir o backend em `http://localhost:8080` com o **profile `dev`** habilitado (veja seção de dados de exemplo abaixo).

## Variáveis de ambiente importantes

- Backend (definidas em `docker-compose.yml`):
  - `SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/book_exchange`
  - `SPRING_DATASOURCE_USERNAME=postgres`
  - `SPRING_DATASOURCE_PASSWORD=postgres`
  - `SPRING_PROFILES_ACTIVE=dev`

Caso queira customizar credenciais ou outras configurações, você pode alterar diretamente o arquivo `docker-compose.yml`.

## Acesso aos serviços

- **Backend (API + Swagger)**: `http://localhost:8080` (Swagger em `/swagger-ui.html`)

Para parar os serviços:

```bash
docker compose down
```

Se quiser remover também os dados persistidos do PostgreSQL (volume `db_data`), use:

```bash
docker compose down -v
```

### Build mais rápido com Docker BuildKit

Para acelerar o build das imagens durante o desenvolvimento, você pode habilitar o **Docker BuildKit** antes de subir os serviços:

```bash
export DOCKER_BUILDKIT=1
docker-compose up --build
```


## Executando o frontend (sem Docker)

Para rodar o frontend em modo desenvolvimento, na pasta `frontend`:

```bash
pnpm install
pnpm start
```

O frontend ficará disponível em `http://localhost:4200`, consumindo o backend em `http://localhost:8080`.

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

## Dados de exemplo (seed em desenvolvimento)

Quando o backend é executado com o profile `dev` (por exemplo via `docker compose up` ou localmente com

```bash
SPRING_PROFILES_ACTIVE=dev mvn spring-boot:run
```

), uma rotina de seed (`SampleDataConfig`) popula automaticamente o banco com alguns dados de exemplo:

- **Gêneros**
  - Fantasia
  - Ficção Científica
  - Suspense
  - Romance
- **Usuários**
  - Matheus Silva – `matheus@example.com` – São Paulo
  - Juliana Cristina – `juliana@example.com` – Rio de Janeiro
  - Ricardo Menezes – `ricardo@example.com` – São Paulo
- **Livros**
  - O Hobbit (J.R.R. Tolkien) – Fantasia
  - Duna (Frank Herbert) – Ficção Científica
  - O Homem de Giz (C.J. Tudor) – Suspense
  - Orgulho e Preconceito (Jane Austen) – Romance
- **Estantes (`/shelf`)**
  - Matheus: O Hobbit (colecao), Duna (para_troca)
  - Juliana: Orgulho e Preconceito (colecao), O Hobbit (para_troca)
  - Ricardo: O Homem de Giz (para_troca), Duna (colecao)

Isso facilita testar o backend (e o frontend Angular) imediatamente após subir os serviços, sem precisar cadastrar tudo manualmente.

## Usuários de teste

Use os seguintes e-mails para testar rapidamente o frontend e a API. Todos compartilham a mesma **senha de demonstração**:

- Senha padrão: `123456`

- Matheus Silva – `matheus@example.com` – São Paulo
- Juliana Cristina – `juliana@example.com` – Rio de Janeiro
- Ricardo Menezes – `ricardo@example.com` – São Paulo
- Ana Paula – `ana@example.com` – Curitiba
- Bruno Rocha – `bruno@example.com` – Porto Alegre
- Carla Souza – `carla@example.com` – Belo Horizonte

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

- O sistema agora possui autenticação básica de login por **e-mail + senha** usando hash (BCrypt).
- O cadastro/login principal é feito pelo endpoint híbrido:
  - `POST /auth/register-or-login` – cria o usuário se o e-mail ainda não existir; se já existir, valida a senha e retorna o usuário.
- Também existe um endpoint de login puro:
  - `POST /auth/login`
  - Body de exemplo:
    ```json
    {
      "email": "matheus@example.com",
      "senha": "123456"
    }
    ```
- O frontend (Angular) utiliza esses endpoints para gerenciar sessão do usuário.
