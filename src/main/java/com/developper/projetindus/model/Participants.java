package com.developper.projetindus.model;

public class Participants {
    public long id;

    public String name;
    public boolean isConfirmedAccount;
    public int age;

    public Participants(long id, String name, boolean isConfirmedAccount, int age) {
        this.id = id;
        this.name = name;
        this.isConfirmedAccount = isConfirmedAccount;
        this.age = age;
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

    public boolean isConfirmedAccount() {
        return isConfirmedAccount;
    }

    public void setConfirmedAccount(boolean confirmedAccount) {
        isConfirmedAccount = confirmedAccount;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
