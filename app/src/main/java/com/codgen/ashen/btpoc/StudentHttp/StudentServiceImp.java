package com.codgen.ashen.btpoc.StudentHttp;

import com.codgen.ashen.btpoc.controller.StudentCallBack;
import com.codgen.ashen.btpoc.model.Student;
import com.codgen.ashen.btpoc.model.StudentList;
import com.codgen.ashen.btpoc.network.RestClient;
import com.codgen.ashen.btpoc.network.StudentAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by AsheN on 7/29/2017.
 */

public class StudentServiceImp implements StudentService{

    private StudentAPI studentService;
    private StudentCallBack studentCallBack;

    public StudentServiceImp(StudentAPI service, StudentCallBack callBack) {
        this.studentService = service;
        this.studentCallBack = callBack;
    }

    @Override
    public void getStudentList() {

        final List<Student> students = new ArrayList<>();
        Call<StudentList> call = studentService.getStudentData();

        call.enqueue(new Callback<StudentList>() {
            @Override
            public void onResponse(Call<StudentList> call, Response<StudentList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        students.addAll(response.body().getStudent());
                        studentCallBack.onSuccess(students);
                    }
                }
            }

            @Override
            public void onFailure(Call<StudentList> call, Throwable t) {
                studentCallBack.onFail(t.toString());
                Timber.i("ResponseFailed");
            }
        });
    }
}
