package com.example.book_club_proiect.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "waiting_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, insertable = false)
    private Long user_id;

    @Column(updatable = false, insertable = false)
    private Long book_for_reading;

    private Boolean finished;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User users;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_for_reading")
    private BookOwner bookOwner;


}
