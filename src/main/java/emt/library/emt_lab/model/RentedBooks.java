package emt.library.emt_lab.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class RentedBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateRented;


    @ManyToMany
    private List<Book> books;


    public RentedBooks(){}

    public RentedBooks(List<Book> books){
        this.dateRented = LocalDateTime.now();
        this.books = books;
    }
}
