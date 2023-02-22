package com.example.book_club_proiect.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity(name = "waiting_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, insertable = false, name = "user_id")
    private Long userId;

    @Column(updatable = false, insertable = false,name = "book_for_reading" )
    private Long bookForReading;

    private Boolean finished;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User users;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_for_reading")
    private BookOwner bookOwner;


}
