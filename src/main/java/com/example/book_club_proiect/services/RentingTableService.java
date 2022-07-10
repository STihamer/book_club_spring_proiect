package com.example.book_club_proiect.services;


import com.example.book_club_proiect.models.RentingPeriods;
import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.BookRepository;
import com.example.book_club_proiect.repositories.RentingPeriodsRepository;
import com.example.book_club_proiect.repositories.RentingTableRepository;
import com.example.book_club_proiect.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentingTableService {

    @Autowired
    private final RentingTableRepository rentingTableRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final RentingPeriodsRepository rentingPeriodsRepository;

    public RentingTableService(RentingTableRepository rentingTableRepository, UserRepository userRepository,
                               BookRepository bookRepository, RentingPeriodsRepository rentingPeriodsRepository) {
        this.rentingTableRepository = rentingTableRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.rentingPeriodsRepository = rentingPeriodsRepository;
    }

    public List<RentingTable> getAll() {
        return rentingTableRepository.findAll();
    }


    public Optional<RentingTable> getById(Long id) {

        return rentingTableRepository.findById(id);
    }

    public RentingTable createMyRentingTable(Long borrowed_by, Long book_id,
                                             Long renting_period) {
        RentingTable rentingTable = new RentingTable();
        rentingTable.setBorrowed_by(borrowed_by);
        rentingTable.setBook_id(book_id);
        rentingTable.setBorrowed_date(LocalDate.now());
        rentingTable.setRenting_period(renting_period);
        rentingTable.setReturn_date(LocalDate.now().plusWeeks(renting_period));
        rentingTable.setUsers(userRepository.findById(borrowed_by).get());
        rentingTable.setBooks(bookRepository.findById(book_id).get());
        rentingTable.setRenting_periods(rentingPeriodsRepository.findById(renting_period).get());
        return rentingTableRepository.saveAndFlush(rentingTable);
    }

    public Optional<RentingTable> getByIdForChangingRentingPeriod(Long id, Long return_date) {
        RentingTable rentingTable = rentingTableRepository.findById(id).get();
        rentingTable.setReturn_date(rentingTable.getReturn_date().plusWeeks(return_date));
        System.out.println(rentingTable);
        return rentingTableRepository.findById(id);
    }

    public RentingTable findRentingTableByIdAndChangeRenting_period(Long id, Long period) {
        RentingTable rentingTable = rentingTableRepository.findRentingTableByIdAndChangeRenting_period(id);
        rentingTable.setReturn_date(rentingTable.getReturn_date().plusWeeks(period));
        BeanUtils.copyProperties(rentingTable, "id");
        return rentingTableRepository.saveAndFlush(rentingTable);

    }

    public void deleteRentingTableById(Long id) {
        rentingTableRepository.deleteById(id);
    }
}
