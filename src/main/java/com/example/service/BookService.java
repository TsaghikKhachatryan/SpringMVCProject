package com.example.service;

import com.example.model.Author;
import com.example.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    public void initBooks() {
        books.add(new Book(1, "Crime and Punishment", Arrays.asList(new Author("Fyodor ", "Dostoyevsky"))));
        books.add(new Book(2, "The Picture of Dorian Gray", Arrays.asList(new Author(" Oscar", " Wilde"))));
        books.add(new Book(3, "Gone with the Wind", Arrays.asList(new Author("Margaret", "Mitchell"))));
    }

    public Book createNewBook(int id, String name, List<Author> authors) {
        return new Book(id, name, authors);
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(books -> id == books.getId())
                .findAny()
                .orElse(null);
    }

    public Book getBookByAuthor(Author author) {
        return books.stream()
                .filter(books -> books.getAuthors().contains(author))
                .findAny()
                .orElse(null);
    }

    public Book deleteBook(int id) {
        Book book = books.stream()
                .filter(books -> id == books.getId())
                .findAny()
                .orElse(null);
        if (book != null) {
            books.remove(book);
        }
        return book;
    }

    public Book updateBook(Book updatedBook) {
        Book book = books.stream()
                .filter(books -> updatedBook.getId() == books.getId())
                .findAny()
                .orElse(null);

        if (book != null) {
            book.setName(updatedBook.getName());
            book.setAuthors(updatedBook.getAuthors());
        }
        return book;
    }
}
