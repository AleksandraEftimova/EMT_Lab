package emt.library.emt_lab.service;

import emt.library.emt_lab.model.Author;
import emt.library.emt_lab.model.Book;
import emt.library.emt_lab.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, Integer availableCopies,
                        /*Category category*/ Long authorId);



    Optional<Book> edit(Long id, String name, Integer availableCopies,
                        /*Category category*/ Long authorId);

    void deleteById(Long id);

    void rent();
}
