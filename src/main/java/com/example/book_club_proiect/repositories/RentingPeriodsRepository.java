package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.models.RentingPeriods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RentingPeriodsRepository extends JpaRepository<RentingPeriods, Long> {
}
