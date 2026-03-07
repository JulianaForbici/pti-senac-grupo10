package com.example.bookexchange.config;

import com.example.bookexchange.model.Book;
import com.example.bookexchange.model.Genre;
import com.example.bookexchange.model.User;
import com.example.bookexchange.model.UserShelf;
import com.example.bookexchange.service.BookService;
import com.example.bookexchange.service.GenreService;
import com.example.bookexchange.service.UserService;
import com.example.bookexchange.service.UserShelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class SampleDataConfig {

    private static final Logger log = LoggerFactory.getLogger(SampleDataConfig.class);

    @Bean
    CommandLineRunner sampleDataRunner(
            UserService userService,
            GenreService genreService,
            BookService bookService,
            UserShelfService shelfService,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            log.info("Running sample data seeder (dev profile)...");

            // Avoid inserting duplicates when the database already has users
            if (!userService.findAll().isEmpty()) {
                log.info("Sample users already exist, skipping seeder.");
                return;
            }

            // Genres
            Genre fantasia = new Genre();
            fantasia.setNomeGenero("Fantasia");
            fantasia = genreService.save(fantasia);

            Genre ficcao = new Genre();
            ficcao.setNomeGenero("Ficção Científica");
            ficcao = genreService.save(ficcao);

            Genre suspense = new Genre();
            suspense.setNomeGenero("Suspense");
            suspense = genreService.save(suspense);

            Genre romance = new Genre();
            romance.setNomeGenero("Romance");
            romance = genreService.save(romance);

            Genre terror = new Genre();
            terror.setNomeGenero("Terror");
            terror = genreService.save(terror);

            Genre tecnologia = new Genre();
            tecnologia.setNomeGenero("Tecnologia");
            tecnologia = genreService.save(tecnologia);

            // Users (all with the same demo password: 123456)
            User matheus = new User();
            matheus.setNome("Matheus Silva");
            matheus.setEmail("matheus@example.com");
            matheus.setCidade("São Paulo");
            matheus.setPasswordHash(passwordEncoder.encode("123456"));
            matheus = userService.save(matheus);

            User juliana = new User();
            juliana.setNome("Juliana Cristina");
            juliana.setEmail("juliana@example.com");
            juliana.setCidade("Rio de Janeiro");
            juliana.setPasswordHash(passwordEncoder.encode("123456"));
            juliana = userService.save(juliana);

            User ricardo = new User();
            ricardo.setNome("Ricardo Menezes");
            ricardo.setEmail("ricardo@example.com");
            ricardo.setCidade("São Paulo");
            ricardo.setPasswordHash(passwordEncoder.encode("123456"));
            ricardo = userService.save(ricardo);

            User ana = new User();
            ana.setNome("Ana Paula");
            ana.setEmail("ana@example.com");
            ana.setCidade("Curitiba");
            ana.setPasswordHash(passwordEncoder.encode("123456"));
            ana = userService.save(ana);

            User bruno = new User();
            bruno.setNome("Bruno Rocha");
            bruno.setEmail("bruno@example.com");
            bruno.setCidade("Porto Alegre");
            bruno.setPasswordHash(passwordEncoder.encode("123456"));
            bruno = userService.save(bruno);

            User carla = new User();
            carla.setNome("Carla Souza");
            carla.setEmail("carla@example.com");
            carla.setCidade("Belo Horizonte");
            carla.setPasswordHash(passwordEncoder.encode("123456"));
            carla = userService.save(carla);

            // Books
            Book hobbit = new Book();
            hobbit.setTitulo("O Hobbit");
            hobbit.setAutor("J.R.R. Tolkien");
            hobbit.setGenero(fantasia);
            hobbit = bookService.save(hobbit);

            Book duna = new Book();
            duna.setTitulo("Duna");
            duna.setAutor("Frank Herbert");
            duna.setGenero(ficcao);
            duna = bookService.save(duna);

            Book homemDeGiz = new Book();
            homemDeGiz.setTitulo("O Homem de Giz");
            homemDeGiz.setAutor("C.J. Tudor");
            homemDeGiz.setGenero(suspense);
            homemDeGiz = bookService.save(homemDeGiz);

            Book orgulho = new Book();
            orgulho.setTitulo("Orgulho e Preconceito");
            orgulho.setAutor("Jane Austen");
            orgulho.setGenero(romance);
            orgulho = bookService.save(orgulho);

            Book it = new Book();
            it.setTitulo("It");
            it.setAutor("Stephen King");
            it.setGenero(terror);
            it = bookService.save(it);

            Book dracula = new Book();
            dracula.setTitulo("Drácula");
            dracula.setAutor("Bram Stoker");
            dracula.setGenero(terror);
            dracula = bookService.save(dracula);

            Book steveJobs = new Book();
            steveJobs.setTitulo("Steve Jobs");
            steveJobs.setAutor("Walter Isaacson");
            steveJobs.setGenero(romance); // tratado aqui como biografia geral
            steveJobs = bookService.save(steveJobs);

            Book cleanCode = new Book();
            cleanCode.setTitulo("Clean Code");
            cleanCode.setAutor("Robert C. Martin");
            cleanCode.setGenero(tecnologia);
            cleanCode = bookService.save(cleanCode);

            // Mais livros para enriquecer o catálogo
            Book sociedadeAnel = new Book();
            sociedadeAnel.setTitulo("O Senhor dos Anéis: A Sociedade do Anel");
            sociedadeAnel.setAutor("J.R.R. Tolkien");
            sociedadeAnel.setGenero(fantasia);
            sociedadeAnel = bookService.save(sociedadeAnel);

            Book harryPotter = new Book();
            harryPotter.setTitulo("Harry Potter e a Pedra Filosofal");
            harryPotter.setAutor("J.K. Rowling");
            harryPotter.setGenero(fantasia);
            harryPotter = bookService.save(harryPotter);

            Book neuromancer = new Book();
            neuromancer.setTitulo("Neuromancer");
            neuromancer.setAutor("William Gibson");
            neuromancer.setGenero(ficcao);
            neuromancer = bookService.save(neuromancer);

            Book fundacao = new Book();
            fundacao.setTitulo("Fundação");
            fundacao.setAutor("Isaac Asimov");
            fundacao.setGenero(ficcao);
            fundacao = bookService.save(fundacao);

            Book garotaExemplar = new Book();
            garotaExemplar.setTitulo("Garota Exemplar");
            garotaExemplar.setAutor("Gillian Flynn");
            garotaExemplar.setGenero(suspense);
            garotaExemplar = bookService.save(garotaExemplar);

            Book silencioInocentes = new Book();
            silencioInocentes.setTitulo("O Silêncio dos Inocentes");
            silencioInocentes.setAutor("Thomas Harris");
            silencioInocentes.setGenero(suspense);
            silencioInocentes = bookService.save(silencioInocentes);

            Book eleanorPark = new Book();
            eleanorPark.setTitulo("Eleanor & Park");
            eleanorPark.setAutor("Rainbow Rowell");
            eleanorPark.setGenero(romance);
            eleanorPark = bookService.save(eleanorPark);

            Book amigosInfluenciar = new Book();
            amigosInfluenciar.setTitulo("Como Fazer Amigos e Influenciar Pessoas");
            amigosInfluenciar.setAutor("Dale Carnegie");
            amigosInfluenciar.setGenero(tecnologia);
            amigosInfluenciar = bookService.save(amigosInfluenciar);

            // UserShelf entries
            // Matheus: O Hobbit (colecao), Duna (para_troca)
            saveShelfEntry(shelfService, matheus, hobbit, "colecao", "Ótimo estado");
            saveShelfEntry(shelfService, matheus, duna, "para_troca", "Bom estado");

            // Juliana: Orgulho e Preconceito (colecao), O Hobbit (para_troca)
            saveShelfEntry(shelfService, juliana, orgulho, "colecao", "Perfeito, edição de capa dura");
            saveShelfEntry(shelfService, juliana, hobbit, "para_troca", "Bom estado");

            // Ricardo: O Homem de Giz (para_troca), Duna (colecao)
            saveShelfEntry(shelfService, ricardo, homemDeGiz, "para_troca", "Pouco usado");
            saveShelfEntry(shelfService, ricardo, duna, "colecao", "Coleção pessoal");

            // Ana: Steve Jobs (colecao), Clean Code (para_troca)
            saveShelfEntry(shelfService, ana, steveJobs, "colecao", "Subscrito, poucas anotações");
            saveShelfEntry(shelfService, ana, cleanCode, "para_troca", "Marcado, em bom estado");

            // Bruno: It (colecao), Duna (para_troca)
            saveShelfEntry(shelfService, bruno, it, "colecao", "Lido uma vez");
            saveShelfEntry(shelfService, bruno, duna, "para_troca", "Bom estado");

            // Carla: Drácula (para_troca), Orgulho e Preconceito (colecao)
            saveShelfEntry(shelfService, carla, dracula, "para_troca", "Edição antiga");
            saveShelfEntry(shelfService, carla, orgulho, "colecao", "Coleção pessoal, muito bem conservado");

            log.info("Sample data seeder finished.");
        };
    }

    private void saveShelfEntry(
            UserShelfService shelfService,
            User user,
            Book book,
            String disponibilidade,
            String estadoConservacao
    ) {
        UserShelf shelf = new UserShelf();
        shelf.setUsuario(user);
        shelf.setLivro(book);
        shelf.setDisponibilidade(disponibilidade);
        shelf.setEstadoConservacao(estadoConservacao);
        shelfService.save(shelf);
    }
}

