package com.developper.projetindus.dto;

import com.fasterxml.jackson.annotation.JsonCreator;


public class RatingDTO {

    public String idQuestion;
    public Boolean choice;


    @JsonCreator
    public RatingDTO(String idQuestion, Boolean choice) {
        this.idQuestion = idQuestion;
        this.choice = choice;
    }

    public Boolean getChoice() {
        return choice;
    }

    public void setChoice(Boolean choice) {
        this.choice = choice;
    }

    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public String toString() {
        return "RatingDTO{" +
                "idQuestion='" + idQuestion + '\'' +
                ", choice=" + choice +
                '}';
    }
}
