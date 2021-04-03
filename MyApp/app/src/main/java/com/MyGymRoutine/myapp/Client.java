package com.MyGymRoutine.myapp;

import java.util.Date;

public class Client {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Date birthday; // 00/00/0000
    private int phoneNumber; // 000 000 000
    private int personalTrainerId;
    private int height; // cm or m

    public Client(String name, String surname, String username, String password, String email, Date birthday, int phoneNumber, int personalTrainerId) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.personalTrainerId = personalTrainerId;
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getPersonalTrainerId() {
        return personalTrainerId;
    }
}
