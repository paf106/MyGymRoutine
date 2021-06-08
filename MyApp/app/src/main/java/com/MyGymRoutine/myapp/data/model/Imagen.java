package com.MyGymRoutine.myapp.data.model;


import java.io.Serializable;

public class Imagen implements Serializable {
    private String type;
    private byte[] data;

    public Imagen(){}

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
