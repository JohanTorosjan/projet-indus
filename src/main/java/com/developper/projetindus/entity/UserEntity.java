package com.developper.projetindus.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "user",schema = "databaseschema")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;
    @Column(name = "instagram")
    private String instagram;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "confirmed_account")
    private Boolean confirmed_account;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "doc")
    private Date doc;
    @Column(name = "answered_questions")
    private int answered_questions;
    @Column(name = "has_active_session")
    private Boolean has_active_session;

    public UserEntity(){
    }

    public UserEntity(long id, String name, String surname, String instagram, String email, String password, Boolean confirmed_account, Date dob, Date doc, int answered_questions, Boolean has_active_session) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.instagram = instagram;
        this.email = email;
        this.password = password;
        this.confirmed_account = confirmed_account;
        this.dob = dob;
        this.doc = doc;
        this.answered_questions = answered_questions;
        this.has_active_session = has_active_session;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getConfirmed_account() {
        return confirmed_account;
    }

    public void setConfirmed_account(Boolean confirmed_account) {
        this.confirmed_account = confirmed_account;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDoc() {
        return doc;
    }

    public void setDoc(Date doc) {
        this.doc = doc;
    }

    public int getAnswered_questions() {
        return answered_questions;
    }

    public void setAnswered_questions(int answered_questions) {
        this.answered_questions = answered_questions;
    }

    public Boolean getHas_active_session() {
        return has_active_session;
    }

    public void setHas_active_session(Boolean has_active_session) {
        this.has_active_session = has_active_session;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", instagram='" + instagram + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmed_account=" + confirmed_account +
                ", dob=" + dob +
                ", doc=" + doc +
                ", answered_questions=" + answered_questions +
                ", has_active_session=" + has_active_session +
                '}';
    }
}
