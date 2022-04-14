package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.R.id;
import com.group_15.bta.application.Services;
import com.group_15.bta.business.AccessSections;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.SectionListAdapter;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {
    protected String Name;
    protected String Description;
    protected String Category;
    protected ArrayList<Section> sections;
    protected SectionListAdapter sectionsAdapted;
    protected AccessSections sectionList = new AccessSections();

    public CourseActivity(){ sections = new ArrayList<Section>();}
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();//back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Bundle b = getIntent().getExtras();
        this.Name = b.getString("Title"); //should be some global call to get name
        this.Description = b.getString("Description");
        this.Category = b.getString("Category");

        final TextView tView = (TextView)findViewById(id.CourseName);
        final TextView dView = (TextView)findViewById(id.Description);
        tView.setText(this.Name);
        dView.setText(this.Description);

        sections = sectionList.getCourseSections(Name);

        listSections();
    }

    public void buttonAddSection(View v){
        EditText section = (EditText) findViewById(id.SectionNumber);
        EditText startTime = (EditText) findViewById(id.StartTime);
        EditText endTime = (EditText) findViewById(id.EndTime);
        EditText Days = (EditText) findViewById(id.Days);
        EditText CAP = (EditText) findViewById(id.CAP);
        EditText Instructor = (EditText) findViewById(id.Instructor);
        EditText Location = (EditText) findViewById(id.Location);

        if(section.getText().toString().length() != 0 && startTime.getText().toString().length() != 0 &&
            endTime.getText().toString().length() != 0 && Days.getText().toString().length() != 0 &&
            CAP.getText().toString().length() !=0 && Instructor.getText().toString().length() != 0 &&
            Location.getText().toString().length() != 0) {

            String startTimes = startTime.getText().toString();
            String endTimes = endTime.getText().toString();
            String d = Days.getText().toString();
            d = d.replaceAll("\\s+", "");
            String[] ds;
            String[] Time;

            if (sectionList.validateDayAndTime(startTimes, endTimes, d)) {
                try
                {
                    ds = d.split(",");
                    Time = sectionList.timeParser(startTimes, endTimes, ds);
                    int Cap = Integer.parseInt(CAP.getText().toString());
                    Section s = new Section(this.Name + " - " + section.getText().toString(), Instructor.getText().toString(), ds, Time,
                            Location.getText().toString(), Cap, Cap, Name, Category);
                    sectionList.insertSection(s);
                    sections = sectionList.getCourseSections(Name);
                    Services.setCourseToTrue();
                    listSections();
                }
                catch(final Exception e)
                {
                    Messages.fatalError(this, e.getMessage());
                }
            } else {
                Messages.warning(this, "The number of start times have to match the number of end times\nand the days have to be in the correct format");
            }

        }
        else
        {
            Toast.makeText(CourseActivity.this, "Please make sure all fields are filled.",Toast.LENGTH_LONG).show();
        }


    }

    public void buttonDeleteSec(View v){
        EditText section = (EditText) findViewById(id.DelSecNumber);

        for(int i = 0; i<sections.size();i++){
            if(0 == sections.get(i).getSection().compareTo(this.Name + " - " +section.getText().toString())){
                sectionList.deleteSection(sections.get(i));
                sections = sectionList.getCourseSections(Name);
            }
        }
        listSections();
    }

    private void listSections(){
        ListView listView = (ListView) findViewById(R.id.sectionsList);
        String sectionName = this.Name;

        sectionsAdapted = new SectionListAdapter(this, R.layout.section_list_item, sections);
        listView.setAdapter(sectionsAdapted);
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
