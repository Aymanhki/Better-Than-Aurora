package com.group_15.bta.presentation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.R.layout;
import com.group_15.bta.business.AccessDegrees;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.objects.Student;
import com.group_15.bta.persistence.StudentPersistence;

import java.util.ArrayList;

public class CreateStudentActivity extends AppCompatActivity {
    //private ArrayList<Student> students;
    private AccessStudents accessStudents;
    private TextView studentDegree;
    private Dialog selectADegreeDialog;
    private ListView toSelectDegrees;
    private ArrayAdapter degreesAdapted;
    private EditText newDegree;
    private Button addDegreeBtn;
    private Button cancel;
    private AccessDegrees accessDegrees = new AccessDegrees();
    StudentPersistence students = AccessStudents.getInstance();
    private ArrayList<Student> studentList = students.getStudentList();
    private ArrayAdapter<Student> studentArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bundle bundle =  getIntent().getExtras();
        // students = (ArrayList<Student>)bundle.getSerializable("Students");
        accessStudents = new AccessStudents();
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_admin_create_student);
    }

    public void buttonCreateStudent(View v) {
        EditText editID = (EditText)findViewById(R.id.newID);
        EditText editPassword = (EditText)findViewById(id.newPassword);
        EditText editName = (EditText)findViewById(R.id.newName);

        Student student = new Student(editID.getText().toString(),
                editPassword.getText().toString(),
                editName.getText().toString(),
                studentDegree.getText().toString());
        //students.add(student);
        String result;

        result = validateStudentData(student);
        if (result == null) {
            try
            {
                accessStudents.insertStudent(student);
                studentList = accessStudents.getStudentList();
                Intent createIntent = new Intent(CreateStudentActivity.this, StudentListActivity.class);
                CreateStudentActivity.this.startActivity(createIntent);

            }
            catch(final Exception e)
            {
                Messages.fatalError(this, e.getMessage());
            }
        } else {
            Messages.warning(this, result);
        }
        // StudentPersistence studs = AccessStudents.getInstance();
        //studs.insertStudent(student);
        // finish();
        // Bundle bundle = new Bundle();
        // bundle.putSerializable("Students",students);
        // createIntent.putExtras(bundle);
        // overridePendingTransition(0,0);

    }
    private boolean findStudent(Student student){
        boolean result = false;
        ArrayList<Student> studentFound = new ArrayList<>();
        studentFound = accessStudents.getStudent(student);
        if(studentFound.size() != 0) {result = true;}
        return result;
    }

    private String validateStudentData(Student student) {

        if (student.getID().length() == 0) {
            return "Student name required";
        }

        if (student.getID().length() == 0) {
            return "Student ID required";
        }

        if (student.getPassword().length() == 0) {
            return "Student password required";
        }

        if (findStudent(student)) {
            return "Student ID " + student.getID() + " already exists.";
        }

        if(studentDegree.getText().toString().length() == 0 || !accessDegrees.contains(new Degree(studentDegree.getText().toString())))
        {
            return "Student did not declare a valid degree";
        }

        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish(  );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentDegree = (TextView) findViewById(id.studentDegree);
        studentDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectANewDegree();
            }
        });
    }

    private void selectANewDegree()
    {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View selectADegree = getLayoutInflater().inflate(layout.select_a_degree_popup, null);
        dialogBuildr.setView(selectADegree);
        selectADegreeDialog = dialogBuildr.create();
        selectADegreeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        selectADegreeDialog.show();


        toSelectDegrees = (ListView) selectADegree.findViewById(id.to_select_a_degree_from_list);
        Button addANewDegree = (Button) selectADegree.findViewById(id.add_a_new_degree_btn_for_student);

        degreesAdapted = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, accessDegrees.getDegreeListNames());
        toSelectDegrees.setAdapter(degreesAdapted);

        toSelectDegrees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                studentDegree.setText(degreesAdapted.getItem(i).toString());
                selectADegreeDialog.dismiss();
            }
        });

        addANewDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectADegreeDialog.dismiss();
                promptAddNewDegree();
            }
        });

    }

    private void promptAddNewDegree() {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View enterADegreePopUp = getLayoutInflater().inflate(R.layout.add_a_degree_popup, null);
        dialogBuildr.setView(enterADegreePopUp);
        AlertDialog dialog = dialogBuildr.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        newDegree = (EditText) enterADegreePopUp.findViewById(R.id.new_degree_text_box);
        addDegreeBtn = (Button) enterADegreePopUp.findViewById(R.id.add_a_degree_btn);
        cancel = (Button) enterADegreePopUp.findViewById(R.id.cancel_add_a_degree_button);

        addDegreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newDegree.getText().toString().length() > 0 && !accessDegrees.contains(new Degree(newDegree.getText().toString()))) {
                    addAnewDegree();
                    dialog.dismiss();
                } else {
                    Toast.makeText(CreateStudentActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        newDegree.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == event.KEYCODE_ENTER) {
                    addAnewDegree();
                    dialog.dismiss();
                }
                return false;
            }
        });
    }

    private void addAnewDegree()
    {
        accessDegrees.insert(new Degree(newDegree.getText().toString()));
        degreesAdapted = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, accessDegrees.getDegreeListNames());
        toSelectDegrees.setAdapter(degreesAdapted);
        selectADegreeDialog.show();
    }
}