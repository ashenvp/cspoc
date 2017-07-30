package com.codgen.ashen.btpoc.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codgen.ashen.btpoc.R;
import com.codgen.ashen.btpoc.model.Device;
import com.codgen.ashen.btpoc.model.Student;

import java.util.List;
import java.util.Objects;


/**
 * Created by AsheN on 7/29/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> implements CompoundButton
        .OnCheckedChangeListener {


    private List<Student> students;
    private Context context;
    private int count = 0;
    private List<Device> deviceList;

    public StudentAdapter(Context context, List<Student> students, List<Device> deviceList) {
        this.students = students;
        this.context = context;
        this.deviceList = deviceList;
    }


    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_student_list, parent, false);
        return new StudentAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(final StudentAdapter.ViewHolder holder, final int position) {
        if(studentAvilability(students.get(position).getId())){
            holder.tvStudent.setText(students.get(position).getName());
            holder.tvStudent.setTextColor(Color.BLUE);
            holder.chkboxStatus.setChecked(true);
        }else{
            holder.tvStudent.setText(students.get(position).getName());
            holder.tvStudent.setTextColor(Color.RED);
            holder.chkboxStatus.setChecked(false);
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean studentAvilability(String bluetoothId){
        boolean availability = false;

        for (int i = 0; i <deviceList.size() ; i++) {
            availability = Objects.equals(bluetoothId, deviceList.get(i).getAddress());
        }
        return availability;
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
        TextView tvStudent;
        CheckBox chkboxStatus;

        public ViewHolder(View view) {
            super(view);

            tvStudent = (TextView) view.findViewById(R.id.text_view_student);
            chkboxStatus = (CheckBox) view.findViewById(R.id.check_box_student);

            chkboxStatus.setOnCheckedChangeListener(this);
        }

        @Override
        public void onClick(View v) {
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


        }
    }

}
