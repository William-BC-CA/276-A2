package com.asn2.cmpt276.asn2.models;

import jakarta.persistence.*;


// Formulates table for the database
@Entity
@Table(name = "students")
public class Student {
    // Used to create unique id on add student
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private double weight;
    private double height;
    private String hair_colour;
    private double gpa;
    
    // Constructor
    public Student() {
    }

    // Full constructor
    public Student(String name, double weight, double height, String hair_colour, double gpa) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hair_colour = hair_colour;
        this.gpa = gpa;
    }

    // Getters and setters
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
