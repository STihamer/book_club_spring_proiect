package com.example.book_club_proiect.services;



import com.example.book_club_proiect.models.BookOwner;

import com.example.book_club_proiect.repositories.BookOwnerRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookOwnerService {
    @Autowired //toDo -injection by constructor
    private final BookOwnerRepository book_ownerRepository;

    public BookOwnerService(BookOwnerRepository book_ownerRepository) {
        this.book_ownerRepository = book_ownerRepository;
    }

    public List<BookOwner> getAll() {
        return book_ownerRepository.findAll();
    }

    public Optional<BookOwner> getById(Long id) {

        return book_ownerRepository.findById(id);
    }


    // camelCase instead of underscore
    public BookOwner createBook_Owner(BookOwner book_owner) {
        return book_ownerRepository.saveAndFlush(book_owner);
    }

    public void deleteById(Long id) {
        book_ownerRepository.deleteById(id);
    }

    public BookOwner updateBookOwner(Long id, BookOwner bookOwner) {
        BookOwner existingSession = book_ownerRepository.findById(id).get();
        BeanUtils.copyProperties(bookOwner, existingSession, "id");
        return book_ownerRepository.saveAndFlush(existingSession);
    }

}
