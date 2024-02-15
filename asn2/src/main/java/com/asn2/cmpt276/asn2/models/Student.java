package com.asn2.cmpt276.asn2.models;

public class Student {
    private String name;
    private double weight;
    private double height;
    private String hair_colour;
    private double gpa;
    
    public Student() {
    }

    public Student(String name, double weight, double height, String hair_colour, double gpa) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hair_colour = hair_colour;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHair_colour() {
        return hair_colour;
    }

    public void setHair_colour(String hair_colour) {
        this.hair_colour = hair_colour;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    
}
