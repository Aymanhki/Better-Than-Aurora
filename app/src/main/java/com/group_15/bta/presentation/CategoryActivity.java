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
    ArrayAdapter<String> degreesAdapted;
    AccessDegrees accessDegrees = new AccessDegrees();
    public CategoryActivity() {
        courses = new ArrayList<>();
    }
    CourseListAdapter coursesAdapted;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

            Bundle b = getIntent().getExtras();
            this.Name = b.getString("Title"); //should be some global call to get name

            ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        final TextView tView = findViewById(R.id.CategoryName);
            tView.setText(this.Name);

            courses = courseList.getCategoryCourses(Name);

            listCourses();

            ListView listView = findViewById(R.id.coursesList);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Bundle b1 = new Bundle();
                Intent i = new Intent(CategoryActivity.this, CourseActivity.class);
                b1.putString("Title",courses.get(position).getID());
                b1.putString("Description",courses.get(position).getDescription());
                b1.putString("Category", Name);
                i.putExtras(b1);
                startActivity(i);
            });
        }


        public void buttonAddCourse(View v){
            EditText CourseID = findViewById(R.id.CourseID);
            EditText CourseName = findViewById(R.id.CourseName);
            EditText CourseDescription = findViewById(R.id.CourseDescription);
            EditText CourseCreditHours = findViewById(R.id.CourseCreditHours);
            EditText TuitionFee = findViewById(R.id.TuitionFee);
            int CH;
            double TF;
            if(CourseCreditHours.getText().toString().length() > 0) {
                CH = Integer.parseInt(CourseCreditHours.getText().toString());
            }
            else { CH = -1;}
            if(TuitionFee.getText().toString().length() > 0) {
                TF = Double.parseDouble(TuitionFee.getText().toString());;
            }
            else { TF = 0;}
            Course c = new Course(CourseID.getText().toString(), CourseName.getText().toString(), CourseDescription.getText().toString(), CH, Name, TF, courseDegrees.getText().toString());

            String result = validateCourse(c);

            if(result == null) {
                courseList.insertCourses(c);
            }
            else
            {
                Messages.warning(this, result);
            }
            courses = courseList.getCategoryCourses(Name);
            listCourses();
        }
        private String validateCourse(Course course){
            if(course.getID().length() == 0 || course.getID().length()>9 || findCourse(course))
            {
                return "Valid Course ID required";
            }
            if(course.getTitle().length() == 0)
            {
                return "Course Name required";
            }
            if(course.getDescription().length() == 0)
            {
                return "Course Description required";
            }
            if (course.getCreditHours() == -1)
            {
                return "Course Hours required";
            }
            if(course.getTuition() == 0)
            {
                return "Tuition Fee required";
            }
            if(course.getAssociatedDegree().length() ==0){
                return "Associated Degree required";
            }
            return null;
        }
        private boolean findCourse(Course course){
        boolean result = false;
        ArrayList<Course> courseFound;
        courseFound = courseList.getCategoryCourses(Name);
        for(int i = 0; i<courseFound.size();i++){
            if(courseFound.get(i).getID().equals(course.getID())){
                result = true;
            }
        }
        return result;
        }

    public void buttonDeleteCourse(View v){
        EditText CourseID = findViewById(R.id.DeleteCourseID);

        for(int i =0; i< courses.size();i++){
            if(0 == courses.get(i).getID().compareTo(CourseID.getText().toString())){
                courseList.deleteCourses(courses.get(i));
                courses = courseList.getCategoryCourses(Name);
            }
        }
        listCourses();
    }

    public void listCourses(){
        ListView listView = findViewById(R.id.coursesList);
        coursesAdapted = new CourseListAdapter(this, R.layout.course_list_item, courses);
        listView.setAdapter(coursesAdapted);
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
        courseDegrees = findViewById(R.id.courseDegree);
        courseDegrees.setOnClickListener(view -> selectANewDegree());

        courseDegrees.setOnFocusChangeListener((view, b) -> {
            if(b)
            {
                selectANewDegree();
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


        toSelectDegrees = selectADegree.findViewById(R.id.to_select_degrees_list);
        Button doneBtn = selectADegree.findViewById(R.id.done_selecting_degrees);
        Button addANewDegree = selectADegree.findViewById(R.id.add_a_new_degree_btn);

        degreesAdapted = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, accessDegrees.getDegreeListNames());
        toSelectDegrees.setAdapter(degreesAdapted);
        toSelectDegrees.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        addANewDegree.setOnClickListener(view -> {
            selectADegreeDialog.dismiss();
            promptAddNewDegree();

        });

        doneBtn.setOnClickListener(view -> {
            ArrayList<Integer> positions = getSelectedItems();
            StringBuilder selectedDegrees = new StringBuilder();
            for(int i=0; i<positions.size(); i++)
            {

                if(i < positions.size() - 1)
                {
                    selectedDegrees.append(degreesAdapted.getItem( positions.get(i))).append(", ");
                }
                else
                {
                    selectedDegrees.append(degreesAdapted.getItem(positions.get(i)));
                }
            }
            selectADegreeDialog.dismiss();
            courseDegrees.setText(selectedDegrees.toString());
        });
    }

    private void promptAddNewDegree() {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View enterADegreePopUp = getLayoutInflater().inflate(R.layout.add_a_degree_popup, null);

        dialogBuildr.setView(enterADegreePopUp);
        AlertDialog dialog = dialogBuildr.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        newDegree = enterADegreePopUp.findViewById(R.id.new_degree_text_box);
        addDegreeBtn = enterADegreePopUp.findViewById(R.id.add_a_degree_btn);
        cancel = enterADegreePopUp.findViewById(R.id.cancel_add_a_degree_button);

        addDegreeBtn.setOnClickListener(view -> {
            if (newDegree.getText().toString().length() > 0 && !accessDegrees.contains(new Degree(newDegree.getText().toString()))) {
                accessDegrees.insert(new Degree(newDegree.getText().toString()));
                goBackToPreviousDialog();
                dialog.dismiss();
            } else {
                Toast.makeText(CategoryActivity.this, "Please make sure you enter a new degree.",Toast.LENGTH_LONG).show();
            }
        });

        cancel.setOnClickListener(view -> {
            dialog.dismiss();
            goBackToPreviousDialog();
        });

        newDegree.setOnKeyListener((v, keyCode, event) -> {

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
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
        });
    }

    private void goBackToPreviousDialog()
    {

        degreesAdapted = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, accessDegrees.getDegreeListNames());
        ArrayList<Integer> positions = getSelectedItems();
        toSelectDegrees.setAdapter(degreesAdapted);
        selectADegreeDialog.show();

        for(int i=0; i<positions.size(); i++)
        {
            toSelectDegrees.setSelection(positions.get(i));
           toSelectDegrees.setItemChecked(positions.get(i), true);

        }
    }

    private ArrayList<Integer> getSelectedItems()
    {
        ArrayList<Integer> toReturn = new ArrayList<>();
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
