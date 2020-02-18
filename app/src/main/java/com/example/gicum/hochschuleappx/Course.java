package com.example.gicum.hochschuleappx;

class Course {
    private String name;
    private String room;
    private String Profesor;
    private String date;
    private String time;

    public Course(String name, String room, String profesor, String date, String time){
        this.name = name;
        this.room = room;
        this.Profesor = profesor;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfesor() {
        return Profesor;
    }

    public void setProfesor(String profesor) {
        Profesor = profesor;
    }

    public String getDate() {
        return date;
    }
    public String getTime(){
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
