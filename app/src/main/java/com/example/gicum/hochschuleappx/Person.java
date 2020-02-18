package com.example.gicum.hochschuleappx;

import java.util.List;

class Person {
    private String user;
    private String pass;
    private String study;
    private String name;
    private int matr;
    private int semestr;
    private List<Course> courses;


    public Person(String username, String pass, String name, String study, int matrik, int semester, List<Course> l) {
        this.user = username;
        this.pass = pass;
        this.study = study;
        this.matr = matrik;
        this.semestr = semester;
        this.name = name;
        this.courses = l;
    }

    public String getPass(){
        return pass;
    }
    public void setPass(String pass) { this.pass = pass; }
    public String getName(){
        return name;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) { this.user = user; }
    public String getStudy() {
        return study;
    }
    public int getMatr() {
        return matr;
    }
    public int getSemestr() {
        return semestr;
    }
    public List<Course> getCourses(){
        return courses;
    }
}
