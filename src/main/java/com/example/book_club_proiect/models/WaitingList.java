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
    private Integer user_id;
    private Integer book_id;
    @Column(updatable = false, insertable = false)
    private Integer available_after;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="available_after")
    private RentingTable rentingTable;
}
