package com.example.book_club_proiect.services;

import com.example.book_club_proiect.dto.BookDTO;
import com.example.book_club_proiect.mapper.BookMapper;
import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.BookOwner;
import com.example.book_club_proiect.models.MyListing;
import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.repositories.BookOwnerRepository;
import com.example.book_club_proiect.repositories.BookRepository;
import com.example.book_club_proiect.repositories.MyListingRepository;
import com.example.book_club_proiect.repositories.RentingTableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    private final MyListingRepository myListingRepository;
    private final BookOwnerRepository bookOwnerRepository;
    private final RentingTableRepository rentingTableRepository;

    public BookService(BookMapper bookMapper, BookRepository bookRepository, MyListingRepository myListingRepository,
                       BookOwnerRepository bookOwnerRepository,
                       RentingTableRepository rentingTableRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;

        this.myListingRepository = myListingRepository;
        this.bookOwnerRepository = bookOwnerRepository;
        this.rentingTableRepository = rentingTableRepository;
    }

    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }

    public Optional<BookDTO> getById(Long id) {
        Book book = bookRepository.findById(id).get();
        BookDTO bookDTO = bookMapper.toDto(book);
        return Optional.of(bookDTO);
    }

    public BookDTO createBook(BookDTO createDTO) throws UnsupportedOperationException {

        List<Book> books = bookRepository.findBooksByBookTitle(createDTO.getBookTitle());
        if (books.size() > 0) {

            throw new UnsupportedOperationException(
                    "A book with this title  : " + createDTO.getBookTitle() + " already exists");
        }
        Book bookToSave = bookMapper.toEntity(createDTO);
        Book savedBook = bookRepository.save(bookToSave);
        return bookMapper.toDto(savedBook);

    }
    public Book deleteBookById(Long id) throws UnsupportedOperationException {
        List<MyListing> myListings = myListingRepository.findMyListingsByBookTitle(id);
        List<BookOwner> bookOwners = bookOwnerRepository.findBookOwnersByBookId(id);
        List<RentingTable> rentingTables = rentingTableRepository.findRentingTablesByBookId(id);

        if (myListings.size() > 0 || bookOwners.size() > 0 || rentingTables.size() > 0) {
            throw new UnsupportedOperationException("Sorry, you can not delete book: " + ". It " +
                    "is used in another table.");
        }
        bookRepository.deleteById(id);
        return null;
    }

    public BookDTO updateBook(Long id, BookDTO bookToBeUpdated) throws UnsupportedOperationException {
        Optional<Book> oldBookOptional = bookRepository.findById(id);
        if (oldBookOptional.isPresent()) {
            Book oldBook = oldBookOptional.get();
            oldBook.setAuthorFname(bookToBeUpdated.getAuthorFirstName());
            oldBook.setAuthorLname(bookToBeUpdated.getAuthorLastName());
            oldBook.setBookTitle(bookToBeUpdated.getBookTitle());
            oldBook.setPublished(bookToBeUpdated.getPublishedYear());
            Book savedBook = bookRepository.save(oldBook);
            BookDTO bookDTO = bookMapper.toDto(savedBook);
            return bookDTO;
        } else {
            throw new UnsupportedOperationException("Invalid id " + bookToBeUpdated.getBookId());
        }
    }

    public List<BookDTO> findBookByTitleOrByAuthorName(String searching) {
        List<BookDTO> newBookList = new ArrayList<>();
        List<BookDTO> newBookList1 = new ArrayList<>();
        List<BookDTO> newBookList2 = new ArrayList<>();
        List<BookDTO> newBookList3 = new ArrayList<>();
        newBookList = this.bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
        if (searching.length() > 2) {
            newBookList1 =
                    newBookList.stream().filter(element -> element.getBookTitle()
                            .toLowerCase().contains(searching
                                    .toLowerCase())).collect(Collectors.toList());


        }
        if (newBookList1.size() < 1 && searching.length() > 2) {
            newBookList2 =
                    newBookList.stream().filter(element -> element.getAuthorFirstName()
                            .toLowerCase().contains(searching.
                                    toLowerCase())).collect(Collectors.toList());
        }
        if (newBookList2.size() < 1 && searching.length() > 2) {
            newBookList3 =
                    newBookList.stream().filter(element -> element.getAuthorLastName().
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
