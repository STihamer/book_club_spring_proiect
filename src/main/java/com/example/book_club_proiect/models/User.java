package com.example.book_club_proiect.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Schema(description = "All details about the user. ")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Schema(description = "The first name can not contain numbers")
    @Pattern(regexp = "[A-Za-z]{2,}", message = "The first name can not contain numbers")
    private String first_name;
    @Schema(description = "The last name can not contain numbers")
    @Pattern(regexp = "[A-Za-z]{2,}", message = "The Last name can not contain numbers")
    private String last_name;
    private Integer user_age;

    @Schema(description = "username is unique")
    @Column(unique = true)
    private String username;
    @Schema(description = "The email must fulfill some requirements")
    @Pattern(regexp = "^[a-z\\d._\\-]{3,25}@[a-z\\d\\-]{3,8}\\.[a-z]{2,3}$", message = "The user email " +
            "must fulfill some requirements")
    private String user_email;

    @Schema(description= "password need to contain at least a number, a lower case and an upper case")
    private String user_password;
    @Column(updatable = false, insertable = false)
    private Long role_id;
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> books;


    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<RentingTable> rentingTableList;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<WaitingList> waitingLists;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<MyListing> myListingList;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "role_Id", nullable = false)
    private Roles roles;
}
