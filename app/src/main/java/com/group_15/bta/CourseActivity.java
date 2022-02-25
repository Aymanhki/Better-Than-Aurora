package com.group_15.bta;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.group_15.bta.R;
import com.group_15.bta.R.id;

public class CourseActivity extends AppCompatActivity {
    protected String Name;
    protected String Description;
    protected ArrayList<Section> sections;
    protected ArrayAdapter arrayAdapter;

    public CourseActivity(){ sections = new ArrayList<Section>();}
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();//back button
        actionBar.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        Bundle b = getIntent().getExtras();
        this.Name = b.getString("Title"); //should be some global call to get name
        this.Description = b.getString("Description");

        final TextView tView = (TextView)findViewById(id.CourseName);
        final TextView dView = (TextView)findViewById(id.Description);
        tView.setText(this.Name);
        dView.setText(this.Description);

        String[] days = {"M","W","F"};
        String[] time = {"10:45am", "11:45am"};

        Section s = new Section("A01",days, time, 66);
        sections.add(s);
        days= new String[]{"T", "TR"};
        time= new String[]{"11:00am", "12:00pm"};

        s = new Section("A02",days,time,66);
        sections.add(s);

        ListView listView = (ListView) findViewById(R.id.sectionsList);
        String sectionName = this.Name;
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, sections) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                String keyPoints = sections.get(position).getSection() + " " + sectionName + " Instructor: TBD  Location: TBD";
                String info = "Day(s): " + sections.get(position).getDays() + " Time: " + sections.get(position).getTime() + " CAP: " + sections.get(position).getCap();

                text1.setText(keyPoints);
                text2.setText(info);

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
    }

    public void buttonAddSection(View v){
        EditText section = (EditText) findViewById(id.SectionNumber);
        EditText startTime = (EditText) findViewById(id.StartTime);
        EditText endTime = (EditText) findViewById(id.EndTime);
        EditText Days = (EditText) findViewById(id.Days);
        EditText CAP = (EditText) findViewById(id.CAP);

        String[] Time = new String[2];
        Time[0] = startTime.getText().toString();
        Time[1] = endTime.getText().toString();

        int Cap = Integer.parseInt(CAP.getText().toString());

        String[] ds;
        String d = Days.getText().toString();

        ds = d.split(" ");

        Section s = new Section(section.getText().toString(), ds, Time, Cap);

        sections.add(s);

        ListView listView = (ListView) findViewById(R.id.sectionsList);
        String sectionName = this.Name;
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, sections) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                String keyPoints = sections.get(position).getSection() + " " + sectionName + " Instructor: TBD  Location: TBD";
                String info = "Day(s): " + sections.get(position).getDays() + " Time: " + sections.get(position).getTime() + " CAP: " + sections.get(position).getCap();

                text1.setText(keyPoints);
                text2.setText(info);

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);

    }
    public void buttonDeleteSec(View v){
        EditText section = (EditText) findViewById(id.DelSecNumber);

        for(int i = 0; i<sections.size();i++){
            if(0 == sections.get(i).getSection().compareTo(section.getText().toString())){
                sections.remove(i);
            }
        }
        ListView listView = (ListView) findViewById(R.id.sectionsList);
        String sectionName = this.Name;
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, sections) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                String keyPoints = sections.get(position).getSection() + " " + sectionName + " Instructor: TBD  Location: TBD";
                String info = "Day(s): " + sections.get(position).getDays() + " Time: " + sections.get(position).getTime() + " CAP: " + sections.get(position).getCap();

                text1.setText(keyPoints);
                text2.setText(info);

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);

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
