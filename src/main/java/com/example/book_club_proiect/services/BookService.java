package com.example.book_club_proiect.services;

import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Long id) {

        return bookRepository.findById(id);
    }

    public Book createBook(final Book book) {
        return bookRepository.saveAndFlush(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Long id, Book book) {
       Book existingBook = bookRepository.findById(id).get();
        BeanUtils.copyProperties(book, existingBook, "id");
        return bookRepository.saveAndFlush(existingBook);
    }
}
