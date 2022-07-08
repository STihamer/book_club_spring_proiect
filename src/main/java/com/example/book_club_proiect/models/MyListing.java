package com.example.book_club_proiect.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "my_listing")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MyListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, insertable = false)
    private Long reading_person;

    @Column(updatable = false, insertable = false)
    private Long book_title;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="reading_person")
    private User users;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_title")
    private Book books;


}
