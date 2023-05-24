package com.developper.projetindus.dto;

import com.fasterxml.jackson.annotation.JsonCreator;


public class RatingDTO {

    public int idQuestion;
    public Boolean choice;


    @JsonCreator
    public RatingDTO(int idQuestion, Boolean choice) {
        this.idQuestion = idQuestion;
        this.choice = choice;
    }

    public Boolean getChoice() {
        return choice;
    }

    public void setChoice(Boolean choice) {
        this.choice = choice;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
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
