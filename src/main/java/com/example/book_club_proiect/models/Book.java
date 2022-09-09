package com.example.book_club_proiect.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//toDo -serializable out, collections are final
public class Book implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookTitle;
    private String authorFname;
    private String authorLname;
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
