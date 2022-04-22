package com.group_15.bta.presentation;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
    protected TextView daysSelector;
    protected TextView timePicker;
    protected Section.availableSectionTimes selectedTime;
    protected String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    protected ArrayAdapter<Section.availableSectionDays> daysAdapted;
    protected ListView daysList;
    protected ListView timesList;
    protected Button doneSelectingDays;
    protected ArrayAdapter<Section.availableSectionTimes> sectionTimesAdapted;
    protected Section.availableSectionDays[] selectedDays;
    public CourseActivity(){ sections = new ArrayList<>();}
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();//back button
        assert actionBar != null;
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
        EditText section = (EditText) findViewById(id.SectionID);
        EditText CAP = (EditText) findViewById(id.CAP);
        EditText Instructor = (EditText) findViewById(id.Instructor);
        EditText Location = (EditText) findViewById(id.Location);

        if(section.getText().toString().length() != 0  && daysSelector.getText().toString().length() != 0 &&
            CAP.getText().toString().length() !=0 && Instructor.getText().toString().length() != 0 && timePicker.getText().toString().length() > 0 &&
            Location.getText().toString().length() != 0) {

            try
            {
                int Cap = Integer.parseInt(CAP.getText().toString());
                Section s = new Section(this.Name + " - " + section.getText().toString(), Instructor.getText().toString(), selectedDays, selectedTime,
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
        }
        else
        {
            Toast.makeText(CourseActivity.this, "Please make sure all fields are filled.",Toast.LENGTH_LONG).show();
        }


    }

    public void buttonDeleteSec(View v){
        EditText section = (EditText) findViewById(id.DelSecID);

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


        sectionsAdapted = new SectionListAdapter(this, R.layout.section_list_item, sections);
        listView.setAdapter(sectionsAdapted);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onResume() {
        super.onResume();
        daysSelector = (TextView) findViewById(id.Days);
        daysSelector.setOnClickListener(view -> selectDays());
        daysSelector.setOnFocusChangeListener((view, b) -> {
            if(b)
            {
                selectDays();
            }
        });


        timePicker = findViewById(id.section_time_picker);
        timePicker.setOnClickListener(view -> selectTime());
        timePicker.setOnFocusChangeListener((view, b) -> {
            if(b)
            {
                selectTime();
            }
        });

    }

    private void selectDays()
    {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View selectDaysPopUp = getLayoutInflater().inflate(R.layout.select_section_days_popup, null);
        daysList = selectDaysPopUp.findViewById(id.to_select_days_from_list);
        daysAdapted = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, Section.availableSectionDays.values());
        daysList.setAdapter(daysAdapted);
        daysList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        doneSelectingDays = selectDaysPopUp.findViewById(id.done_selecting_days_btn);
        dialogBuildr.setView(selectDaysPopUp);
        AlertDialog dialog = dialogBuildr.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        doneSelectingDays.setOnClickListener(view -> {
            SparseBooleanArray checked = daysList.getCheckedItemPositions();
            ArrayList<Section.availableSectionDays> selectedDaysList = new ArrayList<>();
            for (int i = 0; i < checked.size(); i++) {
                int position = checked.keyAt(i);
                if (checked.valueAt(i)) {
                    selectedDaysList.add(daysAdapted.getItem(position));
                }
            }

            selectedDays = new Section.availableSectionDays[selectedDaysList.size()];
            for(int i=0; i<selectedDaysList.size(); i++)
            {
                selectedDays[i] = selectedDaysList.get(i);
            }

            daysSelector.setText(Section.toString(selectedDays));
            dialog.dismiss();
        });

    }

    private void selectTime()
    {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View selectTimePopup = getLayoutInflater().inflate(R.layout.select_section_time_popup, null);
        timesList = selectTimePopup.findViewById(id.to_select_time_from_list);
        sectionTimesAdapted = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Section.availableSectionTimes.values());
        timesList.setAdapter(sectionTimesAdapted);
        dialogBuildr.setView(selectTimePopup);
        AlertDialog dialog = dialogBuildr.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        timesList.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedTime = sectionTimesAdapted.getItem(i);
            timePicker.setText(selectedTime.toString());
            dialog.dismiss();
        });
    }


}
