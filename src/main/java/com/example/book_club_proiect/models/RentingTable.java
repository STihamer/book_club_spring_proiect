package com.example.book_club_proiect.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "renting_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentingTable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, insertable = false, name ="borrowed_by")
    private Long borrowedBy;

    @Column(unique = true,updatable = false, insertable = false, name ="book_id")
    private Long bookId;

    @Column(nullable = false, name ="borrowed_date")
    private LocalDate borrowedDate;

    @Column(updatable = false, insertable = false, name ="renting_period")
    private Long rentingPeriod;

    @Column(nullable = false, name ="return_date")
    private LocalDate returnDate;
    @Column(nullable = false, name ="return_date_extended")
    private Boolean returnDateExtended;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book books;

   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name="borrowed_by")
    private User users;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="renting_period")
    private RentingPeriods renting_periods;

}
