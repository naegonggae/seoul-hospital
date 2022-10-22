package com.lion.domain;

public class Hospital {

    private String id;
    private String address;
    private String district;
    private String category;
    private Integer emergencyRoom;
    private String name;
    private String subdivision;


    public Hospital(String id) {
        this.id = id.replaceAll("\"", "");
    }

    public String getId() {
        return id;
    }
}
