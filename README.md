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

## Tecnologias

- **C# / .NET**
- **PostgreSQL**
- **Blazor** 

---

## Como rodar o projeto

### Pré-requisitos
- .NET SDK
- Banco de dados 

### Executar
```bash
# restaurar dependências
dotnet restore

# build
dotnet build

# rodar
dotnet run --project ./src/NomeDoProjeto
