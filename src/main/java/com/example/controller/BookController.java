package com.example.controller;

import com.example.model.Author;
import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/newBook")
    public String getUser(Model model) {
        model.addAttribute("newBook", bookService.createNewBook(4, "The Stand"
                , Arrays.asList(new Author(" Stephen", " King"))));

        return "newBook";
    }

    @GetMapping("/getBookById")
    public String getBookById(Model model) {
        bookService.initBooks();
        model.addAttribute("book", bookService.getBookById(1));

        return "searchedBookById";
    }

    @GetMapping("/updateBook")
    public String updateUser(Model model) {
        bookService.initBooks();
        Book updatedBook = new Book(1, "Updated Name", Arrays.asList(new Author("Fyodor ", "Dostoyevsky")));
        model.addAttribute("updatedBook", bookService.updateBook(updatedBook));

        return "updatedBook";
    }

    @GetMapping("/getBookByAuthor")
    public String getBookByAuthor(Model model) {
        Author author = new Author("Fyodor ", "Dostoyevsky");
        bookService.initBooks();
        model.addAttribute("book", bookService.getBookByAuthor(author));

        return "searchedBookByAuthor";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(Model model) {
        bookService.initBooks();
      model.addAttribute("book", bookService.deleteBook(1));

        return "deletedBook";
    }
}
