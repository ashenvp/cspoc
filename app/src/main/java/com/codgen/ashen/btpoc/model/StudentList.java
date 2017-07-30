package com.codgen.ashen.btpoc.model;

/**
 * Created by AsheN on 7/29/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentList {

    @SerializedName("data")
    @Expose
    private List<Student> student = null;

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

}