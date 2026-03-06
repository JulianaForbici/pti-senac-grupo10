package com.example.bookexchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookexchange.model.UserShelf;

public interface UserShelfRepository extends JpaRepository<UserShelf, Long> {
    Optional<UserShelf> findByUsuarioIdAndLivroId(Long usuarioId, Long livroId);
}