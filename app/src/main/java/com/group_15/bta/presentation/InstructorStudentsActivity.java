package com.group_15.bta.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.business.AccessStudentSections;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class InstructorStudentsActivity extends AppCompatActivity {

    private AccessStudentSections accessStudentSections;
    private ArrayList<StudentSection> studentSections = new ArrayList<>();

    private Section currentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_students);
        ActionBar actionBar = getSupportActionBar();//back button
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle =  getIntent().getExtras();
        currentSection = (Section) bundle.getSerializable("sectionID");
        displaySectionTitle();
        accessStudentSections = new AccessStudentSections();

        studentSections = accessStudentSections.getStudentsInSection(currentSection.getSection());

        displayList();
    }


    private void displaySectionTitle(){
        final TextView tView = findViewById(R.id.SectionName);
        tView.setText( currentSection.getSection());
    }

    private void displayList(){

        ArrayAdapter<StudentSection> studentArrayAdapter = new ArrayAdapter<StudentSection>(this, android.R.layout.select_dialog_multichoice, android.R.id.text1, studentSections) {
            @SuppressLint("SetTextI18n")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);


                StudentSection studentSection = studentSections.get(position);
                text1.setText(studentSection.getAssociatedStudent() + ": " + studentSection.getGrade());


                return view;
            }
        };

        ListView listView = findViewById(R.id.studentSectionList);
        listView.setAdapter(studentArrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Button updateGradeButton = findViewById(R.id.UpdateGrade);

            updateGradeButton.setEnabled(listView.getCheckedItemCount() > 0);
        });
    }

    private StudentSection createStudentSectionFromEditText(StudentSection student) {
        EditText updateGrade = findViewById(R.id.Grade);

        return new StudentSection(student.getAssociatedStudent(), updateGrade.getText().toString(), student.getSection(), new AccessCourses().getCourse(student.getAssociatedCourse().getID()));
    }

    public void buttonUpdateGrade(View v) {

        ListView listView = findViewById(R.id.studentSectionList);

        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<StudentSection> toUpdate = new ArrayList<>();
        for(int i=0; i<checked.size(); i++)
        {
            int position = checked.keyAt(i);
            if(checked.valueAt(i))
            {
                toUpdate.add(studentSections.get(position));
            }

        }
        updateGrade(toUpdate);
        studentSections = accessStudentSections.getStudentsInSection(currentSection.getSection());
        displayList();

        for(int i=0; i<studentSections.size(); i++)
        {
            listView.setItemChecked(i, false);
        }

        EditText updateGrade = findViewById(R.id.Grade);
        updateGrade.setText(null);

    }

    private void updateGrade(ArrayList<StudentSection> toUpdate)
    {
        EditText updateGrade = findViewById(R.id.Grade);
        String result = validateGradeData(updateGrade.getText().toString().trim());
        Button updateGradeButton = findViewById(R.id.UpdateGrade);
        if(result == null)
        {
            updateGradeButton.setEnabled(false);


            try {
                for(int i=0; i<toUpdate.size(); i++)
                {
                    accessStudentSections.updateStudentSection(createStudentSectionFromEditText(toUpdate.get(i)));
                }
            }
            catch (final Exception e)
            {
                Messages.fatalError(this, e.getMessage());
            }
        }
        else
        {
            Messages.warning(this, result);
        }


    }

    private String validateGradeData(String grade) {

        if (grade.matches("[A-C][+]?|F|D")) {
            return null;
        }

        return "Not a valid grade.";
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

