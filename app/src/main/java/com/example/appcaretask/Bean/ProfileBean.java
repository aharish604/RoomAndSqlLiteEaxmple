package com.example.appcaretask.Bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfileBean {

    public int getSno() {
        return Sno;
    }

    public void setSno(int sno) {
        Sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="Sno")
    private int Sno ;

    @ColumnInfo(name = "name")
    private String name = "";

    @ColumnInfo(name = "department")
    private String department = "";

    @ColumnInfo(name = "img_url")
    private String img_url = "";

}
