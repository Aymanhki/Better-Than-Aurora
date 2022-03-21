package com.group_15.bta.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessSections;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Courses;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;

import java.util.ArrayList;
import java.util.List;

public class InstructorSectionsActivity extends AppCompatActivity {
    private Instructor instructor;
    private Student instructorAccountInstance = new Student();
    private AccessUsers instructorLogInInstance = new AccessUsers(instructorAccountInstance);
    private Instructor currentUser = (Instructor) new AccessUsers().getCurrentUser();

    private AccessSections accessSections;
    private ArrayList<Section> sectionList;

    protected ArrayAdapter arrayAdapter;


    private int selectedSectionPosition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*accessSections = new AccessSections();
        sectionList = accessSections.getSectionList();*/
accessSections = new AccessSections();
        sectionList = accessSections.getSectionList();
        super.onCreate(savedInstanceState);
        instructor = (Instructor) getIntent().getSerializableExtra(instructorLogInInstance.getUserTypeString(instructorAccountInstance));
        setContentView(R.layout.activity_instructor_sections);
        ActionBar actionBar = getSupportActionBar();//back button
        actionBar.setDisplayHomeAsUpEnabled(true);


       listSections();

    }

    private void listSections(){
        if (!sectionList.isEmpty()) {
            ListView listView = (ListView) findViewById(R.id.listSections);
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, sectionList) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    String keyPoints = sectionList.get(position).getSection() + " " + " Location: TBD";
                    String info = " Day(s): " + sectionList.get(position).getDays() + " Time: " + sectionList.get(position).getTime() + " CAP: " + sectionList.get(position).getCap();

                    text1.setText(sectionList.get(position).getAssociatedCategory() + ": " + sectionList.get(position).getAssociatedCourse());
                    text2.setText(keyPoints + info);

                    return view;
                }
            };
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == selectedSectionPosition) {
                        listView.setItemChecked(position, false);
                        selectedSectionPosition = -1;
                    } else {
                        listView.setItemChecked(position, true);
                        selectedSectionPosition = position;

                        Intent editIntent = new Intent(InstructorSectionsActivity.this, InstructorStudentsActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("sectionID", sectionList.get(selectedSectionPosition));
                        editIntent.putExtras(bundle);
                        InstructorSectionsActivity.this.startActivity(editIntent);
                    }


                }
            });
        }
        else {
            ArrayList<String> noSections = new ArrayList<>();
            noSections.add("No assigned sections.");
            ListView listView = (ListView) findViewById(R.id.listSections);
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, noSections) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText("No assigned sections.");
                    text2.setText("Contact an administrator for more information.");

                    return view;
                }
            };
            listView.setAdapter(arrayAdapter);

        }
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

