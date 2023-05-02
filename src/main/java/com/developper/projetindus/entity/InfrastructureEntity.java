package com.developper.projetindus.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "infrastructure",schema = "databaseschema")
public class InfrastructureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "adresse")
    private String adresse;

    @ManyToOne
    @JoinColumn(name ="id_schedule")
    private ScheduleEntity schedule;

    @ManyToOne
    @JoinColumn(name="id_type")
    private TypeInfrastructureEntity type;

    public InfrastructureEntity(long id, String name, String adresse, ScheduleEntity schedule, TypeInfrastructureEntity type) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.schedule = schedule;
        this.type = type;
    }

    public InfrastructureEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public TypeInfrastructureEntity getType() {
        return type;
    }

    public void setType(TypeInfrastructureEntity type) {
        this.type = type;
    }
}
