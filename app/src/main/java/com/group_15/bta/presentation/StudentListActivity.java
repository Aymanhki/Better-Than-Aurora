package com.group_15.bta.presentation;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.objects.Student;

import java.util.ArrayList;


public class StudentListActivity extends AppCompatActivity {
    private AccessStudents accessStudents;
    private ArrayList<Student> students = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_list);
        accessStudents = new AccessStudents();

        students = accessStudents.getStudentList();
        listStudents();
    }

    private void listStudents(){
        ArrayAdapter<Student> studentArrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, students) {
            @SuppressLint("SetTextI18n")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(students.get(position).getName());
                text2.setText("ID: " + students.get(position).getID() + ", " + "Degree: " + students.get(position).getAssociatedDegree());

                return view;
            }
        };

        final ListView listView = findViewById(id.listStudent);
        listView.setAdapter(studentArrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent editIntent = new Intent(StudentListActivity.this, EditStudentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("Position", position);
            editIntent.putExtras(bundle);
            StudentListActivity.this.startActivity(editIntent);
            listView.setItemChecked(position, false);
        });
    }

    public void buttonCreateStudentOnClick(View v) {
        Intent createIntent = new Intent(StudentListActivity.this, CreateStudentActivity.class);
        StudentListActivity.this.startActivity(createIntent);
    }

    public void buttonDeleteStudent(View v) {
        EditText StudentID = findViewById(id.DeleteStudentID);

        String result;

        result = validateStudentData(StudentID.getText().toString());
        if (result == null) {
            try {
                accessStudents.deleteStudentID(StudentID.getText().toString());
                String successfulDeleteMessage = "Student: " + StudentID.getText().toString() + " deleted.";
                Toast.makeText(StudentListActivity.this, successfulDeleteMessage, Toast.LENGTH_SHORT).show();
                students = accessStudents.getStudentList();

            } catch (final Exception e) {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
            Messages.warning(this, result);
        }
        students = accessStudents.getStudentList();
        listStudents();
        StudentID.setText(null);
    }

    public String validateStudentData(String id) {
        ArrayList<Student> student = accessStudents.getStudent(new Student(id));
        if (student.size() == 0) {
            return "Student id: " + id + " not found, no student was deleted.";
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listStudents();
    }
}
