package com.group_15.bta.presentation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.business.AccessDegrees;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.objects.Degree;
import com.group_15.bta.objects.Student;

import java.util.ArrayList;

public class EditStudentActivity extends AppCompatActivity {
    private ArrayList<Student> students;
    private int position;

    private AccessStudents accessStudents;
    private ArrayList<Student> studentList;

    private TextView studentDegree;
    private Dialog selectADegreeDialog;
    private ListView toSelectDegrees;
    private ArrayAdapter degreesAdapted;
    private EditText newDegree;
    private Button addDegreeBtn;
    private Button cancel;
    private AccessDegrees accessDegrees = new AccessDegrees();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        accessStudents = new AccessStudents();
        studentList = accessStudents.getStudentList();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        // StudentPersistence studs = AccessStudents.getInstance();
      //  ArrayList<Student> students = studs.getStudentList();
        //students = (ArrayList<Student>) bundle.getSerializable("Students");
        position = bundle.getInt("Position");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_student);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);
        editName.setText(studentList.get(position).getName());
        editPassword.setText(studentList.get(position).getPassword());
        TextView editDegree = (TextView) findViewById(id.studentDegree);
        editDegree.setText(studentList.get(position).getAssociatedDegree());
        final TextView tView = (TextView)findViewById(id.studentID);
        tView.setText(studentList.get(position).getPassword());

    }

    public void buttonEditStudent(View v) {
       // StudentPersistence studs = AccessStudents.getInstance();
        //studs.deleteStudent(studs.getStudentList().get(position));
        // students.remove(position);
        EditText editName = (EditText) findViewById(R.id.editStudentName);
        EditText editPassword = (EditText) findViewById(id.editStudentPassword);
        Editable name = editName.getText();
        Editable password = editPassword.getText();
        final TextView tView = (TextView)findViewById(id.studentID);
        String id = (String) tView.getText();

        Student student = new Student(id, password.toString(), name.toString(), studentDegree.getText().toString());
        // students.add(student);
        accessStudents = new AccessStudents();
        accessStudents.updateStudent(student);
        //studs.updateStudent(student);
        Intent createIntent = new Intent(EditStudentActivity.this, StudentListActivity.class);
        //finish();
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("Students",students);
        //createIntent.putExtras(bundle);
        EditStudentActivity.this.startActivity(createIntent);
        //overridePendingTransition(0,0);
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

        studentDegree.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    selectANewDegree();
                }
            }
        });
    }

    private void selectANewDegree()
    {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View selectADegree = getLayoutInflater().inflate(R.layout.select_a_degree_popup, null);
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
                    accessDegrees.insert(new Degree(newDegree.getText().toString()));
                    goBackToPreviousDialog();
                    dialog.dismiss();
                } else {
                    Toast.makeText(EditStudentActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                goBackToPreviousDialog();
            }
        });

        newDegree.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == event.KEYCODE_ENTER) {
                    if (newDegree.getText().toString().length() > 0 && !accessDegrees.contains(new Degree(newDegree.getText().toString()))) {
                        accessDegrees.insert(new Degree(newDegree.getText().toString()));
                        goBackToPreviousDialog();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(EditStudentActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });
    }

    private void goBackToPreviousDialog()
    {

        degreesAdapted = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, accessDegrees.getDegreeListNames());
        toSelectDegrees.setAdapter(degreesAdapted);
        selectADegreeDialog.show();
    }
}
