package com.developper.projetindus.model;

public class QuestionCategory {

    public int id_category;


    public String name;

    public int weight;


    public QuestionCategory() {
    }

    public QuestionCategory(int id_category, String name, int weight) {
        this.id_category = id_category;
        this.name = name;
        this.weight = weight;
    }



    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "QuestionCategory{" +
                "id_category=" + id_category +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
}
