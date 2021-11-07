/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author HL2020
 */
public class Member {
    private int id;
    private String name;
    private boolean gender;
    private int age;
    private Date dob;
    private String weight;
    private String smm;
    private String bodyfat;
    private Class clas;
    ArrayList<PTranier> PT = new ArrayList<>();

    public Member() {
    }

    public Member(int id, String name, boolean gender, int age, Date dob, String weight, String smm, String bodyfat, Class clas) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dob = dob;
        this.weight = weight;
        this.smm = smm;
        this.bodyfat = bodyfat;
        this.clas = clas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSmm() {
        return smm;
    }

    public void setSmm(String smm) {
        this.smm = smm;
    }

    public String getBodyfat() {
        return bodyfat;
    }

    public void setBodyfat(String bodyfat) {
        this.bodyfat = bodyfat;
    }

    public Class getClas() {
        return clas;
    }

    public void setClas(Class clas) {
        this.clas = clas;
    }

    public ArrayList<PTranier> getPT() {
        return PT;
    }

    public void setPT(ArrayList<PTranier> PT) {
        this.PT = PT;
    }

   
    
}
