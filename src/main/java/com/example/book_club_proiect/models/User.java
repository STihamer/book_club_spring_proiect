package com.example.book_club_proiect.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Schema(description = "All details about the user. ")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Schema(description = "The first name can not contain numbers")
    @Pattern(regexp = "[A-Za-z]{2,}", message = "The first name can not contain numbers")
    @Column(name = "first_name")
    private String firstName;
    @Schema(description = "The last name can not contain numbers")
    @Pattern(regexp = "[A-Za-z]{2,}", message = "The Last name can not contain numbers")
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_age")
    private Integer userAge;

    @Schema(description = "username is unique")
    @Column(unique = true)
    private String username;
    @Schema(description = "The email must fulfill some requirements")
    @Pattern(regexp = "^[a-z\\d._\\-]{3,25}@[a-z\\d\\-]{3,8}\\.[a-z]{2,3}$", message = "The user email " +
            "must fulfill some requirements")
    @Column(name = "user_email")
    private String userEmail;

    @Schema(description= "password need to contain at least a number, a lower case and an upper case")
    @Column(name = "user_password")
    private String userPassword;
    @Column(updatable = false, insertable = false, name="role_id")
    private Long roleId;



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
