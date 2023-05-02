package com.developper.projetindus.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "answer_usages_questions",schema = "databaseschema")
public class AnswerUsagesQuestionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "label")
    private String label;

    @ManyToOne
    @JoinColumn(name="id_usages_questions")
    private UsagesQuestionsEntity usagesQuestions;



    public AnswerUsagesQuestionsEntity() {

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

    public UsagesQuestionsEntity getUsagesQuestions() {
        return usagesQuestions;
    }

    public void setUsagesQuestions(UsagesQuestionsEntity usagesQuestions) {
        this.usagesQuestions = usagesQuestions;
    }

    public AnswerUsagesQuestionsEntity(long id, String label, UsagesQuestionsEntity usagesQuestions) {
        this.id = id;
        this.label = label;
        this.usagesQuestions = usagesQuestions;
    }
}
