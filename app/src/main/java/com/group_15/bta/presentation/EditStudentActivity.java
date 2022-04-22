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

    private AccessStudents accessStudents;

    private TextView studentDegree;
    private Dialog selectADegreeDialog;
    private ListView toSelectDegrees;
    private ArrayAdapter<String> degreesAdapted;
    private EditText newDegree;
    private final AccessDegrees accessDegrees = new AccessDegrees();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        accessStudents = new AccessStudents();
        ArrayList<Student> studentList = accessStudents.getStudentList();

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        int position = bundle.getInt("Position");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_student);
        EditText editName = findViewById(id.editStudentName);
        EditText editPassword = findViewById(id.editStudentPassword);
        editName.setText(studentList.get(position).getName());
        editPassword.setText(studentList.get(position).getPassword());
        TextView editDegree = findViewById(id.studentDegree);
        editDegree.setText(studentList.get(position).getAssociatedDegree());
        final TextView tView = findViewById(id.studentID);
        tView.setText(studentList.get(position).getPassword());

    }

    public void buttonEditStudent(View v) {
        EditText editName = findViewById(id.editStudentName);
        EditText editPassword = findViewById(id.editStudentPassword);
        Editable name = editName.getText();
        Editable password = editPassword.getText();
        final TextView tView = findViewById(id.studentID);
        String id = (String) tView.getText();

        Student student = new Student(id, password.toString(), name.toString(), studentDegree.getText().toString());
        accessStudents = new AccessStudents();
        accessStudents.updateStudent(student);
        Intent createIntent = new Intent(EditStudentActivity.this, StudentListActivity.class);
        EditStudentActivity.this.startActivity(createIntent);
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
        studentDegree = findViewById(id.studentDegree);
        studentDegree.setOnClickListener(view -> selectANewDegree());

        studentDegree.setOnFocusChangeListener((view, b) -> {
            if(b)
            {
                selectANewDegree();
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


        toSelectDegrees = selectADegree.findViewById(id.to_select_a_degree_from_list);
        Button addANewDegree = selectADegree.findViewById(id.add_a_new_degree_btn_for_student);

        degreesAdapted = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accessDegrees.getDegreeListNames());
        toSelectDegrees.setAdapter(degreesAdapted);

        toSelectDegrees.setOnItemClickListener((adapterView, view, i, l) -> {
            studentDegree.setText(degreesAdapted.getItem(i));
            selectADegreeDialog.dismiss();
        });

        addANewDegree.setOnClickListener(view -> {
            selectADegreeDialog.dismiss();
            promptAddNewDegree();
        });

    }

    private void promptAddNewDegree() {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View enterADegreePopUp = getLayoutInflater().inflate(R.layout.add_a_degree_popup, null);
        dialogBuildr.setView(enterADegreePopUp);
        AlertDialog dialog = dialogBuildr.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        newDegree = enterADegreePopUp.findViewById(id.new_degree_text_box);
        Button addDegreeBtn = enterADegreePopUp.findViewById(id.add_a_degree_btn);
        Button cancel = enterADegreePopUp.findViewById(id.cancel_add_a_degree_button);

        addDegreeBtn.setOnClickListener(view -> {
            if (newDegree.getText().toString().length() > 0 && !accessDegrees.contains(new Degree(newDegree.getText().toString()))) {
                accessDegrees.insert(new Degree(newDegree.getText().toString()));
                goBackToPreviousDialog();
                dialog.dismiss();
            } else {
                Toast.makeText(EditStudentActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
            }
        });

        cancel.setOnClickListener(view -> {
            dialog.dismiss();
            goBackToPreviousDialog();
        });

        newDegree.setOnKeyListener((v, keyCode, event) -> {

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (newDegree.getText().toString().length() > 0 && !accessDegrees.contains(new Degree(newDegree.getText().toString()))) {
                    accessDegrees.insert(new Degree(newDegree.getText().toString()));
                    goBackToPreviousDialog();
                    dialog.dismiss();
                } else {
                    Toast.makeText(EditStudentActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
                }
            }
            return false;
        });
    }

    private void goBackToPreviousDialog()
    {

        degreesAdapted = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accessDegrees.getDegreeListNames());
        toSelectDegrees.setAdapter(degreesAdapted);
        selectADegreeDialog.show();
    }
}
