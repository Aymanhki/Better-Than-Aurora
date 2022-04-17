package com.group_15.bta.presentation;
//goes with activity_courses

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.business.AccessDegrees;
import com.group_15.bta.objects.Course;
import com.group_15.bta.objects.CourseListAdapter;
import com.group_15.bta.objects.Degree;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    protected String Name;
    private ArrayList<Course> courses;
    protected AccessCourses courseList = new AccessCourses();
    private TextView courseDegrees;
    AlertDialog selectADegreeDialog;
    ListView toSelectDegrees;
    EditText newDegree;
    Button addDegreeBtn;
    Button cancel;
    String previousText;
    ArrayAdapter<String> degreesAdapted;
    AccessDegrees accessDegrees = new AccessDegrees();
    public CategoryActivity() {
        courses = new ArrayList<Course>();
    }
    private final String ADD_A_DEGREE_COMMAND = "Add a Degree...";
    CourseListAdapter coursesAdapted;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

            Bundle b = getIntent().getExtras();
            this.Name = b.getString("Title"); //should be some global call to get name

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        final TextView tView = (TextView)findViewById(R.id.CategoryName);
            tView.setText(this.Name);

            courses = courseList.getCategoryCourses(Name);

            listCourses();

            ListView listView = (ListView) findViewById(R.id.coursesList);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle b = new Bundle();
                    Intent i = new Intent(CategoryActivity.this, CourseActivity.class);
                    b.putString("Title",courses.get(position).getID());
                    b.putString("Description",courses.get(position).getDescription());
                    b.putString("Category", Name);
                    i.putExtras(b);
                    startActivity(i);
                }
            });
        }


        public void buttonAddCourse(View v){
            EditText CourseID = (EditText) findViewById(R.id.CourseID);
            EditText CourseName = (EditText) findViewById(R.id.CourseName);
            EditText CourseDescription = (EditText) findViewById(R.id.CourseDescription);
            EditText CourseCreditHours = (EditText) findViewById(R.id.CourseCreditHours);
            EditText TuitionFee = (EditText) findViewById(R.id.TuitionFee);


            //TODO: Alex, this long if statement should be a boolean function(s)
            // and it should not just check for if the field is empty,
            // for example, a course ID should not be more than 9 chars.
            if(CourseID.getText().toString().length() != 0 && CourseName.getText().toString().length() != 0 &&
                    CourseDescription.getText().toString().length() != 0 && CourseCreditHours.getText().toString().length() != 0
            && TuitionFee.getText().toString().length() !=0 && courseDegrees.getText().toString().length() != 0) {

                double TF = Double.parseDouble(TuitionFee.getText().toString());
                int CH =  Integer.parseInt(CourseCreditHours.getText().toString());
                Course c = new Course(CourseID.getText().toString(), CourseName.getText().toString(), CourseDescription.getText().toString(), CH, Name, TF, courseDegrees.getText().toString());
                courseList.insertCourses(c);
            }
            else
            {
                Toast.makeText(CategoryActivity.this, "Please make sure all fields are filled properly.",Toast.LENGTH_LONG).show();
            }
            courses = courseList.getCategoryCourses(Name);

            listCourses();
        }

    public void buttonDeleteCourse(View v){
        EditText CourseID = (EditText) findViewById(R.id.DeleteCourseID);

        for(int i =0; i< courses.size();i++){
            if(0 == courses.get(i).getID().compareTo(CourseID.getText().toString())){
                courseList.deleteCourses(courses.get(i));
                courses = courseList.getCategoryCourses(Name);
            }
        }
        listCourses();
    }

    public void listCourses(){
        ListView listView = (ListView) findViewById(R.id.coursesList);
        coursesAdapted = new CourseListAdapter(this, R.layout.course_list_item, courses);
        listView.setAdapter(coursesAdapted);
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
        courseDegrees = (TextView) findViewById(R.id.courseDegree);
        courseDegrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectANewDegree();
            }
        });

        courseDegrees.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        final View selectADegree = getLayoutInflater().inflate(R.layout.select_degrees_popup, null);
        dialogBuildr.setView(selectADegree);
        selectADegreeDialog = dialogBuildr.create();
        selectADegreeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        selectADegreeDialog.show();


        toSelectDegrees = (ListView) selectADegree.findViewById(R.id.to_select_degrees_list);
        Button doneBtn = (Button) selectADegree.findViewById(R.id.done_selecting_degrees);
        Button addANewDegree = (Button) selectADegree.findViewById(R.id.add_a_new_degree_btn);

        degreesAdapted = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, accessDegrees.getDegreeListNames());
        toSelectDegrees.setAdapter(degreesAdapted);
        toSelectDegrees.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        addANewDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectADegreeDialog.dismiss();
                promptAddNewDegree();

            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList positions = getSelectedItems();
                String selectedDegrees = "";
                for(int i=0; i<positions.size(); i++)
                {

                    if(i < positions.size() - 1)
                    {
                        selectedDegrees += degreesAdapted.getItem((Integer) positions.get(i))+", ";
                    }
                    else
                    {
                        selectedDegrees += degreesAdapted.getItem((Integer) positions.get(i));
                    }
                }
                selectADegreeDialog.dismiss();
                courseDegrees.setText(selectedDegrees);
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
                    Toast.makeText(CategoryActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
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
                    if(newDegree.getText().toString().length() > 0 && !accessDegrees.contains(new Degree(newDegree.getText().toString())))
                    {
                        accessDegrees.insert(new Degree(newDegree.getText().toString()));
                        goBackToPreviousDialog();
                        dialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(CategoryActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
                    }

                }
                return false;
            }
        });
    }

    private void goBackToPreviousDialog()
    {

        degreesAdapted = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, accessDegrees.getDegreeListNames());
        ArrayList positions = getSelectedItems();
        toSelectDegrees.setAdapter(degreesAdapted);
        selectADegreeDialog.show();

        for(int i=0; i<positions.size(); i++)
        {
            toSelectDegrees.setSelection((Integer) positions.get(i));
           toSelectDegrees.setItemChecked((Integer) positions.get(i), true);

        }
    }

    private ArrayList getSelectedItems()
    {
        ArrayList toReturn = new ArrayList();
        SparseBooleanArray checked = toSelectDegrees.getCheckedItemPositions();

        for(int i=0; i<checked.size(); i++)
        {
            int position = checked.keyAt(i);
            if(checked.valueAt(i))
            {
               toReturn.add(position);
            }
        }

        return toReturn;
    }


}
