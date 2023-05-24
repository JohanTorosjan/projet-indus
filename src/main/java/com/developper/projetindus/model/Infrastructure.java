package com.developper.projetindus.model;

public class Infrastructure {
    public String adresse;
    public String name;
    public String type;

    public Infrastructure(String adresse, String name, String type) {
        this.adresse = adresse;
        this.name = name;
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
