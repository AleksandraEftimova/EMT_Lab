package emt.library.emt_lab.web.controller;

import emt.library.emt_lab.service.RentedBooksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rented-books")
public class RentedBooksController {

    private final RentedBooksService rentedBooksService;

    public RentedBooksController(RentedBooksService rentedBooksService) {
        this.rentedBooksService = rentedBooksService;
    }


    @GetMapping
    public String getBooksPage (@RequestParam(required = false) String error, Model model,
                                  HttpServletRequest req){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("products", this.rentedBooksService.listAllRentedBooks());
        model.addAttribute("bodyContent", "rented-books");
        return "master-template";
    }

    @PostMapping("/add-book/{id}")
    public String addBookToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try {
            this.rentedBooksService.rentBook(id);
            return "redirect:/rented-books";
        } catch (RuntimeException exception){
            return "redirect:/rented-books?error=" + exception.getMessage();
        }
    }
}

