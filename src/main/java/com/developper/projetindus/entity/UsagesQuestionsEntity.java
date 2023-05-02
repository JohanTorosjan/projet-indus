package com.developper.projetindus.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usages_questions",schema = "databaseschema")
public class UsagesQuestionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "label")
    private String label;

    public UsagesQuestionsEntity(long id) {
        this.id = id;
    }

    public UsagesQuestionsEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "UsagesQuestionsEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
