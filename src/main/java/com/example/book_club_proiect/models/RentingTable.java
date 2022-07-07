package com.example.book_club_proiect.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "renting_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentingTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, insertable = false)
    private Integer borrowed_by;

    @Column(unique = true,updatable = false, insertable = false)
    private Integer book_id;

    @Column(nullable = false)
    private LocalDate  borrowed_date;

    @Column(updatable = false, insertable = false)
    private Integer renting_period;

    @Column(nullable = false)
    private LocalDate return_date;


    /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="borrowed_by")
    private User user;*/



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book books;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="borrowed_by")
    private User users;

    /*@OneToMany(mappedBy = "rentingTable")
    private List<WaitingList> waitingListList;*/
}
