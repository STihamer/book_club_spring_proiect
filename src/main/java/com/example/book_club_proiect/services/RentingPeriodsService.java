package com.example.book_club_proiect.services;

import com.example.book_club_proiect.dto.RentingPeriodsDTO;
import com.example.book_club_proiect.mapper.RentingPeriodsMapper;
import com.example.book_club_proiect.models.RentingPeriods;
import com.example.book_club_proiect.repositories.RentingPeriodsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentingPeriodsService {

    private final RentingPeriodsMapper rentingPeriodsMapper;
    private final RentingPeriodsRepository rentingPeriodsRepository;

    public RentingPeriodsService(RentingPeriodsMapper rentingPeriodsMapper, RentingPeriodsRepository rentingPeriodsRepository) {
        this.rentingPeriodsMapper = rentingPeriodsMapper;
        this.rentingPeriodsRepository = rentingPeriodsRepository;
    }


    public List<RentingPeriodsDTO> getAll() {

        return rentingPeriodsRepository.findAll().stream().map(rentingPeriodsMapper::toDto).collect(Collectors.toList());
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
