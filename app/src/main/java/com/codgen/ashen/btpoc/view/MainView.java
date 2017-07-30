package com.codgen.ashen.btpoc.view;

import com.codgen.ashen.btpoc.model.Student;

import java.util.List;

/**
 * Created by AsheN on 7/29/2017.
 */

public interface MainView {
    void showMsg(String msg);
    void displayList(List<Student> studentList);
}
