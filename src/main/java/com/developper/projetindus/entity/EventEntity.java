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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InfrastructureEntity getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(InfrastructureEntity infrastructure) {
        this.infrastructure = infrastructure;
    }

    public LocalDateTime getStarting_acceptation_beginning_hour() {
        return starting_acceptation_beginning_hour;
    }

    public void setStarting_acceptation_beginning_hour(LocalDateTime starting_acceptation_beginning_hour) {
        this.starting_acceptation_beginning_hour = starting_acceptation_beginning_hour;
    }

    public LocalDateTime getStarting_acceptation_ending_hour() {
        return starting_acceptation_ending_hour;
    }

    public void setStarting_acceptation_ending_hour(LocalDateTime starting_acceptation_ending_hour) {
        this.starting_acceptation_ending_hour = starting_acceptation_ending_hour;
    }

    public LocalDateTime getStopping_acceptation_beginning_hour() {
        return stopping_acceptation_beginning_hour;
    }

    public void setStopping_acceptation_beginning_hour(LocalDateTime stopping_acceptation_beginning_hour) {
        this.stopping_acceptation_beginning_hour = stopping_acceptation_beginning_hour;
    }

    public LocalDateTime getStopping_acceptation_ending_hour() {
        return stopping_acceptation_ending_hour;
    }

    public void setStopping_acceptation_ending_hour(LocalDateTime stopping_acceptation_ending_hour) {
        this.stopping_acceptation_ending_hour = stopping_acceptation_ending_hour;
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", infrastructure=" + infrastructure +
                ", starting_acceptation_beginning_hour=" + starting_acceptation_beginning_hour +
                ", starting_acceptation_ending_hour=" + starting_acceptation_ending_hour +
                ", stopping_acceptation_beginning_hour=" + stopping_acceptation_beginning_hour +
                ", stopping_acceptation_ending_hour=" + stopping_acceptation_ending_hour +
                '}';
    }
}
