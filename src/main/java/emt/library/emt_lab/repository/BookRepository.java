package emt.library.emt_lab.repository;

import emt.library.emt_lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    void deleteByName(String name);
}
