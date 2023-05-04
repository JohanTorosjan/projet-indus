package com.developper.projetindus.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "event",schema = "databaseschema")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_infrastructure")
    private InfrastructureEntity infrastructure;

    @Column(name = "starting_acceptation_beginning_hour")
    private LocalDateTime starting_acceptation_beginning_hour;

    @Column(name = "starting_acceptation_ending_hour")
    private LocalDateTime starting_acceptation_ending_hour;

    @Column(name = "stopping_acceptation_beginning_hour")
    private LocalDateTime stopping_acceptation_beginning_hour;

    @Column(name = "stopping_acceptation_ending_hour")
    private LocalDateTime stopping_acceptation_ending_hour;




}
