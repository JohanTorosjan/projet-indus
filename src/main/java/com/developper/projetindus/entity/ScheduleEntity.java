package com.developper.projetindus.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "schedule",schema = "databaseschema")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="monday_opening")
    public LocalTime monday_opening;
    @Column(name="monday_closing")
    public LocalTime monday_closing;
    @Column(name="tuesday_opening")
    public LocalTime tuesday_opening;
    @Column(name="tuesday_closing")
    public LocalTime tuesday_closing;
    @Column(name="wednesday_opening")
    public LocalTime wednesday_opening;
    @Column(name="wednesday_closing")
    public LocalTime wednesday_closing;
    @Column(name="thursday_opening")
    public LocalTime thursday_opening;
    @Column(name="thursday_closing")
    public LocalTime thursday_closing;
    @Column(name="friday_opening")
    public LocalTime friday_opening;
    @Column(name="friday_closing")
    public LocalTime friday_closing;
    @Column(name="saturday_opening")
    public LocalTime saturday_opening;
    @Column(name="saturday_closing")
    public LocalTime saturday_closing;
    @Column(name="sunday_opening")
    public LocalTime sunday_opening;
    @Column(name="sunday_closing")
    public LocalTime sunday_closing;


}
