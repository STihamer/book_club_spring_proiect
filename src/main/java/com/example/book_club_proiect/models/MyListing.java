package com.example.book_club_proiect.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity(name = "my_listing")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MyListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, insertable = false,name = "reading_person")
    private Long readingPerson;

    @Column(updatable = false, insertable = false,name = "book_title")
    private Long bookTitle;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="reading_person")
    private User users;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_title")
    private Book books;


}
