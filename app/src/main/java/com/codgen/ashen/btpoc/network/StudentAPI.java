package com.codgen.ashen.btpoc.network;

import com.codgen.ashen.btpoc.model.Student;
import com.codgen.ashen.btpoc.model.StudentList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AsheN on 7/29/2017.
 */

public interface StudentAPI {

    @GET("/studentdata")
    Call<StudentList> getStudentData();
}
