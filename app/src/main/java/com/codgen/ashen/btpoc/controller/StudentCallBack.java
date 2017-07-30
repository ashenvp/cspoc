package com.codgen.ashen.btpoc.controller;

import com.codgen.ashen.btpoc.model.Student;

import java.util.List;

/**
 * Created by AsheN on 7/29/2017.
 */

public interface StudentCallBack {

    void onSuccess(List<Student> studentList);
    void onFail(String msg);
}
