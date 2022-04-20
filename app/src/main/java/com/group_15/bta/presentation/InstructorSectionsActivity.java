package com.group_15.bta.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessSections;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Instructor;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.SectionListAdapter;

import java.util.ArrayList;

public class InstructorSectionsActivity extends AppCompatActivity {

    private final Instructor currentUser = (Instructor) new AccessUsers().getCurrentUser();

    private ArrayList<Section> sectionList;

    protected SectionListAdapter arrayAdapter;


    private int selectedSectionPosition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AccessSections accessSections = new AccessSections();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_sections);
        ActionBar actionBar = getSupportActionBar();//back button
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        sectionList = accessSections.getInstructorSections(currentUser.getName());

        listSections();

    }

    private void listSections(){
        if (!sectionList.isEmpty()) {
            ListView listView = findViewById(R.id.listSections);
            arrayAdapter = new SectionListAdapter(this, R.layout.section_list_item, sectionList);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {

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


            });
        }
        else {
            ArrayList<String> noSections = new ArrayList<>();
            noSections.add("No assigned sections.");
            ListView listView = findViewById(R.id.listSections);

            listView.setAdapter( new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, noSections) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView text1 = view.findViewById(android.R.id.text1);
                    TextView text2 = view.findViewById(android.R.id.text2);

                    text1.setText(R.string.no_assigned_sections_for_instructor_place_holder);
                    text2.setText(R.string.no_assigned_section_instructor_action);

                    return view;
                }
            });

        }
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
