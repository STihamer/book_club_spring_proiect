package com.example.book_club_proiect.services;

import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.mapper.BookMapper;
import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
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

    public List<Book> findBookByTitleOrByAuthorName(String searching) {
        List<Book> newBookList = new ArrayList<>();
        List<Book> newBookList1 = new ArrayList<>();
        List<Book> newBookList2 = new ArrayList<>();
        List<Book> newBookList3 = new ArrayList<>();
        newBookList = this.bookRepository.findAll();
        if (searching.length() > 2) {
            newBookList1 =
                    newBookList.stream().filter(element -> element.getBook_title()
                            .toLowerCase().contains(searching
                                    .toLowerCase())).collect(Collectors.toList());


        }
        if (newBookList1.size() < 1 && searching.length() > 2) {
            newBookList2 =
                    newBookList.stream().filter(element -> element.getAuthor_fname()
                            .toLowerCase().contains(searching.
                                    toLowerCase())).collect(Collectors.toList());
        }
        if (newBookList2.size() < 1 && searching.length() > 2) {
            newBookList3 =
                    newBookList.stream().filter(element -> element.getAuthor_lname().
                            toLowerCase().contains(searching.
                                    toLowerCase())).collect(Collectors.toList());
        }
        if (newBookList1.size() > 0) {
            return newBookList1;
        } else if (newBookList2.size() > 0) {
            return newBookList2;
        } else if (newBookList3.size() > 0) {
            return newBookList3;
        }
        return new ArrayList<>();
    }
}
