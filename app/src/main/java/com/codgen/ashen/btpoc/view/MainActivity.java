package com.codgen.ashen.btpoc.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codgen.ashen.btpoc.R;
import com.codgen.ashen.btpoc.controller.StudentController;
import com.codgen.ashen.btpoc.controller.StudentControllerImp;
import com.codgen.ashen.btpoc.model.Device;
import com.codgen.ashen.btpoc.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    RecyclerView recyclerViewStudents;
    Button buttonScan;
    Button buttonShow;
    TextView txtDisplay;
    private BluetoothAdapter mBluetoothAdapter;
    StudentController studentController;
    private ArrayList<Device> mDeviceList = new ArrayList<>();
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewStudents = (RecyclerView) findViewById(R.id.recyclerViewList);
        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonShow = (Button) findViewById(R.id.buttonDisplay);
        txtDisplay = (TextView) findViewById(R.id.text_view_display);

        buttonScan.setOnClickListener(this);
        buttonShow.setOnClickListener(this);
        studentController = new StudentControllerImp(this);
        studentController.requestStudentData();


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void displayList(List<Student> studentList) {
        this.studentList =  studentList;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.buttonScan :
                mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                mBluetoothAdapter.startDiscovery();

                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver, filter);
                txtDisplay.setText("No Of Devices: " + String.valueOf(mDeviceList.size()));
                break;

            case R.id.buttonDisplay :
                StudentAdapter studentAdapter = new StudentAdapter(this,studentList,mDeviceList);
                recyclerViewStudents.setLayoutManager(new LinearLayoutManager(this));
                recyclerViewStudents.setItemAnimator(new DefaultItemAnimator());
                recyclerViewStudents.setAdapter(studentAdapter);

        }

    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDeviceList.add(new Device(device.getName(), device.getAddress()));
                Log.i("BT", device.getName() + "\n" + device.getAddress());

            }
        }
    };
}
