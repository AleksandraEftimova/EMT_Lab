package emt.library.emt_lab.web.controller;

import emt.library.emt_lab.model.Author;
import emt.library.emt_lab.model.BookDto;
import emt.library.emt_lab.model.Category;
import emt.library.emt_lab.model.Book;
import emt.library.emt_lab.model.exceptions.BookNotFoundException;
import emt.library.emt_lab.service.AuthorService;
import emt.library.emt_lab.service.BookService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    //injecting dependancies
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping
    private List<Book> findAll(){
        return this.bookService.findAll();
    }
//    public String getBookPage (@RequestParam(required = false) String error, Model model){
//        if (error != null && !error.isEmpty()){
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", error);
//        }
//
//        List<Book> books = this.bookService.findAll();
//        model.addAttribute("books", books);
//        model.addAttribute("bodyContent", "books");
//        return "master-template";
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findByID(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
//    @GetMapping("/add-form")
//    public String addBookPage(Model model){
//        List<Author> authors = this.authorService.findAll();
//        model.addAttribute("categories", Category.values());
//        model.addAttribute("authors", authors);
//        model.addAttribute("bodyContent", "addBook");
//        return "master-template";
//    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
//    public String saveBook(@RequestParam(required = false) Long id,
//                              @RequestParam String name,
//                              @RequestParam Integer availableCopies,
//                              @RequestParam Long authorId){
//        if(id!=null) {
//            this.bookService.edit(id, name, availableCopies, authorId);
//        } else {
//            this.bookService.save(name, availableCopies, authorId);
//        }
//
//        return "redirect:/books";
//    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, BookDto bookDto){
        return this.bookService.edit(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
//    public String editBookPage(@PathVariable Long id, Model model){
//        if (this.bookService.findById(id).isPresent()) {
//            Book book = this.bookService.findById(id).orElseThrow(() -> new BookNotFoundException(id));
//
//            List<Author> authors = this.authorService.findAll();
//            model.addAttribute("authors", authors);
//            model.addAttribute("categories", Category.values());
//            model.addAttribute("book", book);
//            model.addAttribute("bodyContent", "addBook");
//            return "master-template";
//        }
//        return "redirect:/books?error=BookNotFound";
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if (this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }
//    public String deleteBook (@PathVariable Long id){
//        this.bookService.deleteById(id);
//        return "redirect:/books";
//    }


}
