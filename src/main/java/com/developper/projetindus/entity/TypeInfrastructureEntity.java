package com.developper.projetindus.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_infrastructure",schema = "databaseschema")
public class TypeInfrastructureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public TypeInfrastructureEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeInfrastructureEntity() {
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
}
