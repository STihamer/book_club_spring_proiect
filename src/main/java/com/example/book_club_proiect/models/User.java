package com.example.book_club_proiect.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String first_name;
    private String last_name;
    private Integer user_age;
    private String username ;
    private String user_email;
    private String user_password;

    @ManyToMany
    @JoinTable(
            name = "book_owners",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;


    @OneToMany(mappedBy = "user")
    private List<RentingTable> rentingTableList;


    @ManyToMany
    @JoinTable(
            name = "waiting_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> bookList;
}
