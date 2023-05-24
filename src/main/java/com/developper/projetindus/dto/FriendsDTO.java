package com.developper.projetindus.dto;

import java.util.Date;

public class FriendsDTO {

    public long id;
    public String name;
    public String instagram;
    public Date dob;

    public FriendsDTO(long id, String name, String instagram, Date dob) {
        this.id = id;
        this.name = name;
        this.instagram = instagram;
        this.dob = dob;
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

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
