package com.developper.projetindus.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public class UserCreateDTO {


    private String dob;
    private String firebase_id;
    private String email;
    private Boolean hasActiveSession;
    private String instagram;
    private String name;
    private Integer answered_questions;

    public UserCreateDTO() {
    }

    @Override
    public String toString() {
        return "UserCreateDTO{" +
                "firebase_id='" + firebase_id + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", hasActiveSession=" + hasActiveSession +
                ", instagram='" + instagram + '\'' +
                ", name='" + name + '\'' +
                ", answered_questions=" + answered_questions +
                '}';
    }

    public UserCreateDTO(String dob, String firebase_id, String email, Boolean hasActiveSession, String instagram, String name, Integer answered_questions) {
        this.dob = dob;
        this.firebase_id = firebase_id;
        this.email = email;
        this.hasActiveSession = hasActiveSession;
        this.instagram = instagram;
        this.name = name;
        this.answered_questions = answered_questions;
    }

    public String getFirebase_id() {
        return firebase_id;
    }

    public void setFirebaseId(String firebase_id) {
        this.firebase_id = firebase_id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasActiveSession() {
        return hasActiveSession;
    }

    public void setHasActiveSession(Boolean hasActiveSession) {
        this.hasActiveSession = hasActiveSession;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAnswered_questions() {
        return answered_questions;
    }

    public void setAnswered_questions(Integer answered_questions) {
        this.answered_questions = answered_questions;
    }
}
