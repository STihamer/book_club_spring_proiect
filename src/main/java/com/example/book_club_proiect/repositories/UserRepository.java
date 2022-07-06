package com.example.book_club_proiect.repositories;

import com.example.book_club_proiect.models.RentingTable;
import com.example.book_club_proiect.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
