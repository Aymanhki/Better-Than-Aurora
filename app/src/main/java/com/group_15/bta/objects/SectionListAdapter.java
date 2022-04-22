package com.group_15.bta.objects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group_15.bta.R;

import java.util.ArrayList;

public class SectionListAdapter extends ArrayAdapter<Section> {

    private final Context mainContext;
    private final int resource;

    public SectionListAdapter(Context context, int resource, ArrayList<Section> sections)
    {
        super(context, resource, sections);
        mainContext = context;
        this.resource = resource;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String section = getItem(position).getSection();
        String location = getItem(position).getLocation();
        String instructor = getItem(position).getInstructor();
        String time = getItem(position).getTime().toString();
        String days = Section.toString(getItem(position).getDaysRaw());

        int capacity = getItem(position).getCAP();
        int available = getItem(position).getAvailable();
        LayoutInflater inflater = LayoutInflater.from(mainContext);
        convertView = inflater.inflate(resource, parent, false);

        TextView sectionName = convertView.findViewById(R.id.section_name_list_item);
        TextView locationName = convertView.findViewById(R.id.location_name_list_item);
        TextView instructorName = convertView.findViewById(R.id.instructor_name_list_item);
        TextView datesBox = convertView.findViewById(R.id.times_values_list_item);
        TextView capacityNumber = convertView.findViewById(R.id.capacity_number_list_item);
        TextView availableNumber = convertView.findViewById(R.id.available_number_list_item);

        sectionName.setText(sectionName.getText() + "" + section);
        locationName.setText(locationName.getText() + "" + location);
        instructorName.setText(instructorName.getText() + "" + instructor);
        datesBox.setText(datesBox.getText() + days + ":\n" + time);
        capacityNumber.setText(capacityNumber.getText() + "" + capacity);
        availableNumber.setText(availableNumber.getText() + "" + available);


        return convertView;
    }
}
