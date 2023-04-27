package com.developper.projetindus.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="questions",schema = "databaseschema")
public class QuestionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="label")
    private String label;

    @Column(name="choice0")
    private String choice0;
    @Column(name="choice1")
    private String choice1;

    @ManyToMany
    @JoinTable(name="question_category",joinColumns = @JoinColumn(name = "id_question"),
    inverseJoinColumns = @JoinColumn(name = "id_category"))
    private List<QuestionsEntity> category = new ArrayList<>();

    public QuestionsEntity(long id, String label, String choice0, String choice1) {
        this.id = id;
        this.label = label;
        this.choice0 = choice0;
        this.choice1 = choice1;
    }
    public QuestionsEntity() {
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

    public String getChoice0() {
        return choice0;
    }

    public void setChoice0(String choice0) {
        this.choice0 = choice0;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    @Override
    public String toString() {
        return "QuestionsEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", choice0='" + choice0 + '\'' +
                ", choice1='" + choice1 + '\'' +
                '}';
    }
}
