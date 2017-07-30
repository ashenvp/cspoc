package com.codgen.ashen.btpoc.controller;

import com.codgen.ashen.btpoc.model.Device;

import java.util.List;

/**
 * Created by AsheN on 7/29/2017.
 */

public interface StudentController {
    void requestStudentData();
    void processResult(List<Device> deviceList);
    void displayList();
}
