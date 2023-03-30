package emt.library.emt_lab.service;

import emt.library.emt_lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    void deleteById(Long id);
}
