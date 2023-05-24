package com.developper.projetindus.dto;

public class UpdateUserDTO {
    private String firebaseId;
    private String dob;
    private String email;
    private boolean hasActiveSession;
    private String instagram;
    private String name;
    private int answeredQuestions;

    // Constructeur par défaut
    public UpdateUserDTO() {
    }

    @Override
    public String toString() {
        return "UpdateUserDTO{" +
                "firebaseId='" + firebaseId + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", hasActiveSession=" + hasActiveSession +
                ", instagram='" + instagram + '\'' +
                ", name='" + name + '\'' +
                ", answeredQuestions=" + answeredQuestions +
                '}';
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
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

    public boolean isHasActiveSession() {
        return hasActiveSession;
    }

    public void setHasActiveSession(boolean hasActiveSession) {
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

    public int getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(int answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }
}

