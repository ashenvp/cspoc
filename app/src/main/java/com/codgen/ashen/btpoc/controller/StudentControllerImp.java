package com.codgen.ashen.btpoc.controller;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codgen.ashen.btpoc.StudentHttp.StudentService;
import com.codgen.ashen.btpoc.StudentHttp.StudentServiceImp;
import com.codgen.ashen.btpoc.model.Device;
import com.codgen.ashen.btpoc.model.Student;
import com.codgen.ashen.btpoc.network.RestClient;
import com.codgen.ashen.btpoc.network.StudentAPI;
import com.codgen.ashen.btpoc.view.MainView;
import com.codgen.ashen.btpoc.view.StudentAdapter;

import java.util.List;

/**
 * Created by AsheN on 7/29/2017.
 */

public class StudentControllerImp implements StudentController, StudentCallBack {

    private MainView mainView;

    public StudentControllerImp(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void requestStudentData() {
        StudentService service = new StudentServiceImp(new RestClient("http://demo0774731.mockable.io").buildApi(),this);
        service.getStudentList();
    }

    @Override
    public void processResult(List<Device> deviceList) {

    }

    @Override
    public void displayList() {


    }

    @Override
    public void onSuccess(List<Student> studentList) {
        mainView.displayList(studentList);
    }

    @Override
    public void onFail(String msg) {

    }
}
