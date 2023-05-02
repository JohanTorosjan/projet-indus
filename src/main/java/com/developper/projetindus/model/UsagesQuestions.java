package com.developper.projetindus.model;

import com.developper.projetindus.entity.AnswerUsagesQuestionsEntity;
import com.developper.projetindus.entity.QuestionsEntity;
import com.developper.projetindus.entity.UsagesQuestionsEntity;

import java.util.Arrays;
import java.util.List;

public class UsagesQuestions {


    private  UsagesQuestionsEntity question;
    private List<AnswerUsagesQuestionsEntity> answers;


    public UsagesQuestions() {

    }

    public UsagesQuestionsEntity getQuestion() {
        return question;
    }

    public void setQuestion(UsagesQuestionsEntity question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "UsagesQuestions{" +
                "question=" + question +
                ", answers=" + answers +
                '}';
    }

    public List<AnswerUsagesQuestionsEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerUsagesQuestionsEntity> answers) {
        this.answers = answers;
    }
}
