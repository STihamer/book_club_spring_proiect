package com.example.book_club_proiect.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;
    private String book_title;
    private String author_fname;
    private String author_lname;
    private Integer published;

    @ManyToMany
    @JoinTable(
            name = "book_owner",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;




    @JsonIgnore
    @OneToMany(mappedBy = "books")
    private List <RentingTable> rentingTableList;

    @OneToMany(mappedBy = "books")
    @JsonIgnore
    private List<MyListing> myListingList;

}
