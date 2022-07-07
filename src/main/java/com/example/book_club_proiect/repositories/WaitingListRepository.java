package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.models.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {
}
