package emt.library.emt_lab.bootstrap;

import emt.library.emt_lab.model.Author;
import emt.library.emt_lab.model.Book;
import emt.library.emt_lab.model.Category;
import emt.library.emt_lab.model.Country;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books = new ArrayList<>();

    public static List<Author> authors = new ArrayList<>();

    public static List<Country> countries = new ArrayList<>();


    @PostConstruct
    public void init(){
        Country UK = new Country("UK", "Europe");
        Country France = new Country("France", "Europe");
        Country Japan = new Country("Japan", "Asia");
        countries.add(UK);
        countries.add(France);
        countries.add(Japan);


        authors.add(new Author(1L, "Agatha", "Christie", UK));
        authors.add(new Author(2L, "William", "Shakespeare", UK));
        authors.add(new Author(3L, "Victor", "Hugo", France));
        authors.add(new Author(4L, "Haruki", "Murakami", Japan));

        books.add(new Book("Norwegian wood", 4, Category.NOVEL, authors.get(0)));
        books.add(new Book("Les Miserables", 9, Category.NOVEL, authors.get(3)));
        books.add(new Book("Hamlet", 10, Category.DRAMA, authors.get(2)));
        books.add(new Book("Murder on the Orient Express", 2, Category.THRILLER, authors.get(1)));



    }
}
