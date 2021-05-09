package com.MyGymRoutine.myapp.data.model;

import java.util.Date;

public class Client {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String birthday; // 00/00/0000
    private int phoneNumber; // 000000000
    private int personalTrainerId;
    private double height; // cm or m
    private double weight; // Kg
    private String howOftenSport;
    private String pathologies;
    private String image; // Path of the image

    public Client(){ }



    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getPersonalTrainerId() {
        return personalTrainerId;
    }
}
