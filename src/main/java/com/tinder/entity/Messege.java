package com.tinder.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "messeges")
@Getter
@Setter
public class Messege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messege_id")
    private Long id;

    @Column(name = "who_messege")
    private Long whoMessege;
    @Column(name = "whom_messege")
    private Long whomMessege;
    @Column(name = "time")
    private Long time;
    @Column(name = "text")
    private String text;
}
