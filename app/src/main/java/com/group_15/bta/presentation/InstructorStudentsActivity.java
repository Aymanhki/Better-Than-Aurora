package com.group_15.bta.presentation;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private ArrayAdapter<StudentSection> studentArrayAdapter;
    private int selectedStudentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_students);
        ActionBar actionBar = getSupportActionBar();//back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle =  getIntent().getExtras();
        currentSection = (Section) bundle.getSerializable("sectionID");
        displaySectionTitle();
        accessStudentSections = new AccessStudentSections();

        studentSections = accessStudentSections.getStudentsInSection(currentSection.getSection());

        displayList();
    }


    private void displaySectionTitle(){
        final TextView tView = (TextView)findViewById(R.id.SectionName);
        tView.setText( currentSection.getSection());
    }

    private void displayList(){

        studentArrayAdapter = new ArrayAdapter<StudentSection>(this, android.R.layout.select_dialog_multichoice, android.R.id.text1,studentSections){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);


                StudentSection studentSection = studentSections.get(position);
                text1.setText(studentSection.getAssociatedStudent()+": " + studentSection.getGrade());


                return view;
            }
        };

        ListView listView = (ListView)findViewById(R.id.studentSectionList);
        listView.setAdapter(studentArrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Button updateGradeButton = (Button)findViewById(R.id.UpdateGrade);

                if(listView.getCheckedItemCount() > 0)
                {
                    updateGradeButton.setEnabled(true);

                }
                else
                {
                    updateGradeButton.setEnabled(false);
                }
            }
        });
    }

    private StudentSection createStudentSectionFromEditText(StudentSection student) {
        EditText updateGrade = (EditText)findViewById(R.id.Grade);

        StudentSection newStudent = new StudentSection(student.getAssociatedStudent(), updateGrade.getText().toString(), student.getSection(), new AccessCourses().getCourse(student.getAssociatedCourse().getID()));
        return newStudent;
    }

    public void buttonUpdateGrade(View v) {
        Button updateGradeButton = (Button)findViewById(R.id.UpdateGrade);
        ListView listView = (ListView)findViewById(R.id.studentSectionList);

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

        EditText updateGrade = (EditText)findViewById(R.id.Grade);
        updateGrade.setText(null);

    }

    private void updateGrade(ArrayList<StudentSection> toUpdate)
    {
        EditText updateGrade = (EditText)findViewById(R.id.Grade);
        String result = validateGradeData(updateGrade.getText().toString().trim());
        Button updateGradeButton = (Button)findViewById(R.id.UpdateGrade);
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

        if (grade.matches("[A-C][+]?|D|F|D")) {
            return null;
        }

        return "Not a valid grade.";
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
}

