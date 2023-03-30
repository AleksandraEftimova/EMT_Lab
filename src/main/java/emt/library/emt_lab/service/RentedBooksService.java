package emt.library.emt_lab.service;


import emt.library.emt_lab.model.Book;
import emt.library.emt_lab.model.RentedBooks;

import java.util.List;

public interface RentedBooksService {

    List<Book> listAllRentedBooks ();

    RentedBooks rentBook(Long bookId);
}
