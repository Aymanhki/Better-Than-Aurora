package com.group_15.bta;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.group_15.bta.Student;

import com.group_15.bta.R;
import com.group_15.bta.R.id;

public class StudentListActivity extends Activity {

    private ArrayList<Student> studentList;
    private ArrayAdapter<Student> studentArrayAdapter;
    private int selectedStudentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_list);

        Bundle bundle =  getIntent().getExtras();
        if(bundle != null) {
            studentList = (ArrayList<Student>) bundle.getSerializable("Students");
        }
        else {
            studentList = new ArrayList<>();
            Student one = new Student("12", "12", "Jane Doe");
            Student two = new Student("13", "13", "John Doe");
            studentList.add(one);
            studentList.add(two);
        }
        studentArrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, studentList){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
            TextView text2 = (TextView) view.findViewById(android.R.id.text2);

            text1.setText(studentList.get(position).getStudentID());
            text2.setText(studentList.get(position).getStudentName());

            return view;
            }
        };

        final ListView listView = (ListView)findViewById(id.listStudent);
        listView.setAdapter(studentArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == selectedStudentPosition) {
                    listView.setItemChecked(position, false);
                    selectedStudentPosition = -1;
                } else {
                    listView.setItemChecked(position, true);
                    selectedStudentPosition = position;
                }
                Intent editIntent = new Intent(StudentListActivity.this, EditStudentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Students",studentList);
                bundle.putInt("Position", selectedStudentPosition);
                editIntent.putExtras(bundle);
                StudentListActivity.this.startActivity(editIntent);
            }
        });
    }

    public void buttonBackOnClick(View v) {
        Intent backIntent = new Intent(StudentListActivity.this, HomeActivity.class);
        StudentListActivity.this.startActivity(backIntent);
    }

    public void buttonCreateStudentOnClick(View v) {
        Intent createIntent = new Intent(StudentListActivity.this, CreateStudentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Students",studentList);
        createIntent.putExtras(bundle);
        StudentListActivity.this.startActivity(createIntent);
    }
}

