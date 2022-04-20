package com.group_15.bta.presentation;

import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CourseActivity extends AppCompatActivity {
    protected String Name;
    protected String Description;
    protected String Category;
    protected ArrayList<Section> sections;
    protected SectionListAdapter sectionsAdapted;
    protected AccessSections sectionList = new AccessSections();
    protected TextView daysSelector;
    protected TextView startTimePicker;
    protected TextView endTimePicker;
    protected String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    protected ArrayAdapter daysAdapted;
    protected ListView daysList;
    protected Button doneSelectingDays;
    protected String selectedDays = "";
    protected int hour;
    protected int minute;
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
        EditText section = (EditText) findViewById(id.SectionID);
        EditText CAP = (EditText) findViewById(id.CAP);
        EditText Instructor = (EditText) findViewById(id.Instructor);
        EditText Location = (EditText) findViewById(id.Location);

        if(section.getText().toString().length() != 0 && startTimePicker.getText().toString().length() != 0 &&
            endTimePicker.getText().toString().length() != 0 && daysSelector.getText().toString().length() != 0 &&
            CAP.getText().toString().length() !=0 && Instructor.getText().toString().length() != 0 &&
            Location.getText().toString().length() != 0) {

            String startTime = startTimePicker.getText().toString();
            String endTime = endTimePicker.getText().toString();
            String d = daysSelector.getText().toString();
            d = d.replaceAll("\\s+", "");
            String[] ds;
            String Time;

            try
            {
                ds = d.split(",");
                Time = startTime +" - "+endTime;
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

    @Override
    protected void onResume() {
        super.onResume();
        daysSelector = (TextView) findViewById(id.Days);
        startTimePicker = (TextView) findViewById(id.StartTime);
        endTimePicker = (TextView) findViewById(id.EndTime);

        daysSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDays();
            }
        });

        daysSelector.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    selectDays();
                }
            }
        });

        startTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime(startTimePicker);
            }
        });

        startTimePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    pickTime(startTimePicker);
                }
            }
        });

        endTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickTime(endTimePicker);
            }
        });

        endTimePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b)
                {
                    pickTime(endTimePicker);
                }
            }
        });


    }

    private void selectDays()
    {
        AlertDialog.Builder dialogBuildr = new AlertDialog.Builder(this);
        final View selectDaysPopUp = getLayoutInflater().inflate(R.layout.select_section_days_popup, null);
        daysList = selectDaysPopUp.findViewById(id.to_select_a_days_from_list);
        daysAdapted = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, days);
        daysList.setAdapter(daysAdapted);
        daysList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        doneSelectingDays = selectDaysPopUp.findViewById(id.done_selecting_days_btn);
        dialogBuildr.setView(selectDaysPopUp);
        AlertDialog dialog = dialogBuildr.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        doneSelectingDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked = daysList.getCheckedItemPositions();
                ArrayList<String> selectedDaysList = new ArrayList<>();
                for (int i = 0; i < checked.size(); i++) {
                    int position = checked.keyAt(i);
                    if (checked.valueAt(i)) {
                        selectedDaysList.add((String) daysAdapted.getItem(position));
                    }
                }
                selectedDays = "";
                for(int i=0; i<selectedDaysList.size(); i++)
                {
                    selectedDays += selectedDaysList.get(i);

                    if(i < selectedDaysList.size() - 1)
                    {
                        selectedDays += ", ";
                    }
                }

                daysSelector.setText(selectedDays);
                dialog.dismiss();
            }
        });

    }

    private void pickTime(TextView toSet)
    {

        TimePickerDialog timePickerDialog = new TimePickerDialog
                (this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hour = i;
                                minute = i1;
                                String time = hour+":"+minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");

                                try {

                                        Date date = f24Hours.parse(time);
                                        SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                        toSet.setText(f12Hours.format(date));
                                    }
                                    catch (ParseException newException)
                                    {
                                        Messages.fatalError(CourseActivity.this, newException.getMessage());
                                    }
                            }
                        }, 12, 0, false
                );
        timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timePickerDialog.updateTime(hour, minute);
        timePickerDialog.show();
    }
}
