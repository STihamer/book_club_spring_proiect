package com.example.book_club_proiect.services;


import com.example.book_club_proiect.dto.BookOwnerDTO;
import com.example.book_club_proiect.mapper.BookOwnerMapper;
import com.example.book_club_proiect.models.BookOwner;
import com.example.book_club_proiect.repositories.BookOwnerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookOwnerService {

    private final BookOwnerRepository bookOwnerRepository;
    private final BookOwnerMapper bookOwnerMapper;

    public BookOwnerService(BookOwnerRepository bookOwnerRepository, BookOwnerMapper bookOwnerMapper) {
        this.bookOwnerRepository = bookOwnerRepository;
        this.bookOwnerMapper = bookOwnerMapper;
    }

    public List<BookOwnerDTO> getAll() {

        return bookOwnerRepository.findAll().stream().map(bookOwnerMapper::toDto).collect(Collectors.toList());
    }

    public Optional<BookOwnerDTO> getById(Long id) {
        BookOwner bookOwner = bookOwnerRepository.findById(id).get();
        BookOwnerDTO bookOwnerDTO = bookOwnerMapper.toDto(bookOwner);
        return Optional.of(bookOwnerDTO);
    }


    // camelCase instead of underscore
    public BookOwnerDTO createBookOwner(BookOwnerDTO createDTO) throws UnsupportedOperationException {
        List<BookOwner> bookOwners =
                bookOwnerRepository.findBookOwnersByBook_idAndUser_id(createDTO.getBookId(), createDTO.getUserId());

        if (bookOwners.size() > 0) {
            throw new UnsupportedOperationException(
                    "Book owner data with this owner and book title already exists. Please choose another values");
        }
        BookOwner bookOwnerToSave = bookOwnerMapper.toEntity(createDTO);
        BookOwner savedBookOwner = bookOwnerRepository.save(bookOwnerToSave);
        return bookOwnerMapper.toDto(savedBookOwner);
    }

    public void deleteById(Long id) {
        bookOwnerRepository.deleteById(id);
    }

    public BookOwner updateBookOwner(Long id, BookOwner bookOwner) {
        BookOwner existingSession = bookOwnerRepository.findById(id).get();
        BeanUtils.copyProperties(bookOwner, existingSession, "id");
        return bookOwnerRepository.saveAndFlush(existingSession);
    }

}
