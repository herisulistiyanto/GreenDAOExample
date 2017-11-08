package com.binar.academy.mygreendaosample.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by herisulistiyanto on 9/12/17.
 */
@Entity(nameInDb = "Student") //Table name in DB
public class Student {

    @Id(autoincrement = false)   //primary key
    private Long id;
    @Property(nameInDb = "nama") //row name
    private String nama;
    @Property(nameInDb = "gender") //row name
    private String gender;

    @Generated(hash = 2127717744)
    public Student(Long id, String nama, String gender) {
        this.id = id;
        this.nama = nama;
        this.gender = gender;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
