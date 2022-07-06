package com.example.book_club_proiect.services;


import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.models.User;
import com.example.book_club_proiect.repositories.RentingTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentingTableService {

    @Autowired
    private final RentingTableRepository rentingTableRepository;

    public RentingTableService(RentingTableRepository rentingTableRepository) {
        this.rentingTableRepository = rentingTableRepository;
    }

    public List<RentingTable> getAll() {
        return rentingTableRepository.findAll();
    }


    public Optional<RentingTable> getById(Long id) {

        return rentingTableRepository.findById(id);
    }

    public RentingTable createRentingTable(final RentingTable rentingTable) {
        return rentingTableRepository.saveAndFlush(rentingTable);
    }
}
