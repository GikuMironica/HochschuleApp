package com.example.gicum.hochschuleappx;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class users {
    private static Map<String, Person> akks = new HashMap<>();

    public static Map<String, Person> getAkks() {
        return akks;
    }

    static {

            // create courses for one user...
        List<Course> coursesCTS5 = new ArrayList<>();
        coursesCTS5.add(new Course("Mobile App", "C07", "Graf","Thursday","14:00"));
        coursesCTS5.add(new Course("Database Programming", "Q127", "Herbort","Tuesday","08:00"));
        coursesCTS5.add(new Course("Seminar", "V201", "Schied","Monday","08:00"));
        coursesCTS5.add(new Course("C++", "C07", "Baer","Wednesday","09:50"));

            // create 2 users...
        akks.put("admin", new Person("admin","admin"
                ,"Shaggy","Computer Science"
                ,312777,5, coursesCTS5));
        akks.put("student1", new Person("student1","student1","Max"
                 ,"Computer Science", 312999,5,coursesCTS5));
    }



    public static boolean check(String u, String p) {
        Log.d("debug",u);
        try {
            String s1 = akks.get(u).getPass();
                if (s1.equals(p))
                    return true;
                else
                    return false;
        } catch(Exception e){
            Log.d("debug",e.toString());
        }
        return false;

    }
    public static Person acces(String user){
        return akks.get(user);
    }





 }
