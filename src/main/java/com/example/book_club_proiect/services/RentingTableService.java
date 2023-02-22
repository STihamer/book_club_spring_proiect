package com.example.book_club_proiect.services;


import com.example.book_club_proiect.dto.RentingTableDTO;
import com.example.book_club_proiect.mapper.RentingTableMapper;
import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.repositories.BookRepository;
import com.example.book_club_proiect.repositories.RentingPeriodsRepository;
import com.example.book_club_proiect.repositories.RentingTableRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentingTableService {
    private final RentingTableMapper rentingTableMapper;
    List<Book> foundBooks = new ArrayList<>();
    private final RentingTableRepository rentingTableRepository;

    private final UserRepository userRepository;


    private final BookRepository bookRepository;

    private final RentingPeriodsRepository rentingPeriodsRepository;

    public RentingTableService(RentingTableMapper rentingTableMapper, RentingTableRepository rentingTableRepository,
                               UserRepository userRepository,
                               BookRepository bookRepository, RentingPeriodsRepository rentingPeriodsRepository) {
        this.rentingTableMapper = rentingTableMapper;
        this.rentingTableRepository = rentingTableRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.rentingPeriodsRepository = rentingPeriodsRepository;
    }

    public List<RentingTableDTO> getAll() {

        return rentingTableRepository.findAll().stream().map(rentingTableMapper::toDto).collect(Collectors.toList());
    }


    public Optional<RentingTable> getById(Long id) {

        return rentingTableRepository.findById(id);
    }

    public RentingTable createMyRentingTable(Long borrowed_by, Long book_id,
                                             Long renting_period) throws UnsupportedOperationException {
        List<RentingTable> rentingTables = rentingTableRepository.findRentingTablesByBorrowedByAndBookId(borrowed_by,
                book_id);
        List<RentingTable> rentingTables1 = rentingTableRepository.findRentingTablesByBookId(book_id);
        if (rentingTables.size() > 0) {
            throw new UnsupportedOperationException("Sorry it seems this user have already rented this book");
        }
        if (rentingTables1.size() > 0) {
            throw new UnsupportedOperationException("Sorry it seems somebody have already rented this book");
        }
        RentingTable rentingTable = new RentingTable();
        rentingTable.setBorrowedBy(borrowed_by);
        rentingTable.setBookId(book_id);
        rentingTable.setBorrowedDate(LocalDate.now());
        rentingTable.setRentingPeriod(renting_period);
        Long period = rentingPeriodsRepository.findById(renting_period).get().getRentingPeriod();
        rentingTable.setReturnDate(LocalDate.now().plusDays(period));
        rentingTable.setReturnDateExtended(false);
        rentingTable.setUsers(userRepository.findById(borrowed_by).get());
        rentingTable.setBooks(bookRepository.findById(book_id).get());
        rentingTable.setRenting_periods(rentingPeriodsRepository.findById(renting_period).get());
        return rentingTableRepository.saveAndFlush(rentingTable);
    }

    public Optional<RentingTable> getByIdForChangingRentingPeriod(Long id, Long return_date) {
        RentingTable rentingTable = rentingTableRepository.findById(id).get();
        rentingTable.setReturnDate(rentingTable.getReturnDate().plusWeeks(return_date));
        System.out.println(rentingTable);
        return rentingTableRepository.findById(id);
    }

    public RentingTable findRentingTableByIdAndChangeRenting_period(Long id, Long period) {
        RentingTable rentingTable = rentingTableRepository.findRentingTableByIdAndChangeRenting_period(id);
        rentingTable.setReturnDate(rentingTable.getReturnDate().plusDays(period));
        rentingTable.setReturnDateExtended(true);
        return rentingTableRepository.saveAndFlush(rentingTable);

    }

    public void deleteRentingTableById(Long id) {
        rentingTableRepository.deleteById(id);
    }


    public List<RentingTableDTO> findRentingTablesByBookTitleOrAuthorName(String title, String authorFirstName,
                                                                          String authorLastName) {
        this.filterQueryByBookAndAuthorFirstNameAndLastName(title, authorFirstName, authorLastName);
        List<RentingTable> newRentingTableList = rentingTableRepository.findAll();
        List<RentingTable> filteredRentingTable = new ArrayList<>();
        for (RentingTable rt : newRentingTableList) {
            for (Book book : foundBooks) {
                if (rt.getBookId() == book.getBookId()) {
                    filteredRentingTable.add(rt);
                }
            }
        }
        return filteredRentingTable.stream().map(rentingTableMapper::toDto).collect(Collectors.toList());
    }

    public List<Book> filterQueryByBookAndAuthorFirstNameAndLastName(String title, String authorFirstName,
                                                                     String authorLastName) {
        List<Book> foundBooksByTitle = new ArrayList<>();
        if (title.length() > 0) {
            foundBooksByTitle = bookRepository.findBooksByBookTitleContainingIgnoreCase(title);
        }
        List<Book> foundBooksByAuthorFirstName = new ArrayList<>();
        if (foundBooksByTitle.size() > 0 && authorFirstName.length() > 0) {
            foundBooksByAuthorFirstName =
                    foundBooksByTitle.stream().filter(element -> element.getAuthorFname().toLowerCase().contains(authorFirstName.toLowerCase())).collect(Collectors.toList());

        } else if (authorFirstName.length() > 0) {
            foundBooksByAuthorFirstName = bookRepository.findBooksByAuthorFnameContainingIgnoreCase(authorFirstName);
        }

        List<Book> foundBooksByAuthorLastName = new ArrayList<>();
        if (foundBooksByAuthorFirstName.size() > 0 && authorLastName.length() > 0) {
            foundBooksByAuthorLastName =
                    foundBooksByAuthorFirstName.stream().filter(element -> element.getAuthorLname().toLowerCase().contains(authorLastName.toLowerCase())).collect(Collectors.toList());
        } else if (foundBooksByTitle.size() > 0 && authorLastName.length() > 0) {
            foundBooksByAuthorLastName =
                    foundBooksByTitle.stream().filter(element -> element.getAuthorLname().toLowerCase().contains(authorLastName.toLowerCase())).collect(Collectors.toList());
        } else if (authorLastName.length() > 0) {
            foundBooksByAuthorLastName = bookRepository.findBooksByAuthorLnameContainingIgnoreCase(authorLastName);
        }

        if (foundBooksByAuthorLastName.size() > 0) {
            foundBooks = foundBooksByAuthorLastName;
        } else if (foundBooksByAuthorFirstName.size() > 0) {
            foundBooks = foundBooksByAuthorFirstName;
        } else if (foundBooksByTitle.size() > 0) {
            foundBooks = foundBooksByTitle;
        }
        return foundBooks;
    }

}
