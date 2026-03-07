package com.example.bookexchange.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bookexchange.model.UserShelf;
import com.example.bookexchange.repository.UserShelfRepository;

@Service
public class UserShelfService {

    private final UserShelfRepository repository;

    public UserShelfService(UserShelfRepository repository) {
        this.repository = repository;
    }

    public List<UserShelf> findAll() {
        return repository.findAll();
    }

    public UserShelf findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserShelf save(UserShelf shelf) {

        var usuario = shelf.getUsuario();
        var livro = shelf.getLivro();

        if (usuario == null || livro == null || usuario.getId() == null || livro.getId() == null) {
            throw new RuntimeException("Usuário e livro devem ser informados com seus IDs.");
        }

        var existente = repository.findByUsuarioIdAndLivroId(usuario.getId(), livro.getId());

        if (existente.isPresent()) {
            return existente.get();
        }

        return repository.save(shelf);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}