package emt.library.emt_lab.service.implementations;

import emt.library.emt_lab.model.Book;
import emt.library.emt_lab.model.RentedBooks;
import emt.library.emt_lab.service.BookService;
import emt.library.emt_lab.service.RentedBooksService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentedBooksServiceImpl implements RentedBooksService {
    private final BookService bookService;

    public RentedBooksServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public List<Book> listAllRentedBooks() {
        return bookService.findAll();
    }

    //todo
    @Override
    public RentedBooks rentBook(Long bookId) {
        return null;
    }
}
