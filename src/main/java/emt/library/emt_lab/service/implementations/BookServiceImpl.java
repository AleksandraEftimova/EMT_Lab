package emt.library.emt_lab.service.implementations;

import emt.library.emt_lab.model.Author;
import emt.library.emt_lab.model.Book;
import emt.library.emt_lab.model.BookDto;
import emt.library.emt_lab.model.Category;
import emt.library.emt_lab.model.event.BookCreatedEvent;
import emt.library.emt_lab.model.exceptions.AuthorNotFoundException;
import emt.library.emt_lab.model.exceptions.BookNotFoundException;
import emt.library.emt_lab.repository.AuthorRepository;
import emt.library.emt_lab.repository.BookRepository;
import emt.library.emt_lab.service.BookService;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    //zavisnosti
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Integer availableCopies,/*Category category*/ Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        //ako ima ista ne pravi nisto
        this.bookRepository.deleteByName(name);

        Book book = this.bookRepository.save(new Book(name, availableCopies/*, category*/, author));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Integer availableCopies,/*Category category*/ Long authorId) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setAvailableCopies(availableCopies);
//        book.setCategory(category);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    //todo
    @Override
    public void rent() {

    }

    @Override
    @Transactional
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));

        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getAvailableCopies(), author);
        this.bookRepository.save(book);
        //this.refreshMaterializedView();


        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);


    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
//        book.setCategory(bookDto.getCategoryId().compareTo(Category.values().));
        book.setAvailableCopies(bookDto.getAvailableCopies());
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setAuthor(author);

        this.bookRepository.save(book);

        return Optional.of(book);
    }
}
