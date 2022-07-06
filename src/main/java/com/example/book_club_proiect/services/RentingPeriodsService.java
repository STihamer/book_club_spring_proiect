package com.example.book_club_proiect.services;

import com.example.book_club_proiect.models.Book;
import com.example.book_club_proiect.models.RentingPeriods;
import com.example.book_club_proiect.repositories.RentingPeriodsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentingPeriodsService {

    @Autowired
    private final RentingPeriodsRepository rentingPeriodsRepository;

    public RentingPeriodsService(RentingPeriodsRepository rentingPeriodsRepository) {
        this.rentingPeriodsRepository = rentingPeriodsRepository;
    }


    public List<RentingPeriods> getAll() {
        return rentingPeriodsRepository.findAll();
    }

    public Optional<RentingPeriods> getById(Long id) {

        return rentingPeriodsRepository.findById(id);
    }

    public RentingPeriods createRentingPeriod(final RentingPeriods rentingPeriods) {
        return rentingPeriodsRepository.saveAndFlush(rentingPeriods);
    }

    public void deleteRentingPeriod(Long id) {
        rentingPeriodsRepository.deleteById(id);
    }

    public RentingPeriods updateRentingPeriod(Long id, RentingPeriods rentingPeriods) {
        RentingPeriods existingRentingPeriods = rentingPeriodsRepository.findById(id).get();
        BeanUtils.copyProperties(rentingPeriods, existingRentingPeriods, "id");
        return rentingPeriodsRepository.saveAndFlush(rentingPeriods);
    }
}
