package com.group_15.bta.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.group_15.bta.R;

import java.util.ArrayList;
import java.util.List;

public class SectionListAdapter extends ArrayAdapter<Section> {

    private final static String TAG = "SectionListAdapter";
    private Context mainContext;
    private int resource;

    public SectionListAdapter(Context context, int resource, ArrayList<Section> sections)
    {
        super(context, resource, sections);
        mainContext = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String section = getItem(position).getSection();
        String location = getItem(position).getLocation();
        String instructor = getItem(position).getInstructor();
        String[] times = getItem(position).getTimes();
        String[] days = getItem(position).getDaysRaw();
        String dates = "";

        for(int i=0; i<times.length; i++)
        {
            if(i<times.length-1)
            {
                dates = days[i]+": "+times[i]+"\n";
            }
            else
            {
                dates = days[i]+": "+times[i];
            }

        }

        int capacity = getItem(position).getCAP();
        int waitListCapacity = getItem(position).getWaitListCap();
        int available = getItem(position).getAvailable();
        LayoutInflater inflater = LayoutInflater.from(mainContext);
        convertView = inflater.inflate(resource, parent, false);

        TextView sectionName = (TextView) convertView.findViewById(R.id.section_name_list_item);
        TextView locationName = (TextView) convertView.findViewById(R.id.location_name_list_item);
        TextView instructorName = (TextView) convertView.findViewById(R.id.instructor_name_list_item);
        TextView datesBox = (TextView) convertView.findViewById(R.id.times_values_list_item);
        TextView capacityNumber = (TextView) convertView.findViewById(R.id.capacity_number_list_item);
        TextView waitListCapacityNumber = (TextView) convertView.findViewById(R.id.wait_list_capacity_list_item);
        TextView availableNumber = (TextView) convertView.findViewById(R.id.available_number_list_item);

        sectionName.setText(sectionName.getText()+section);
        locationName.setText(locationName.getText()+location);
        instructorName.setText(instructorName.getText()+instructor);
        datesBox.setText(datesBox.getText()+"\n"+dates);
        capacityNumber.setText(capacityNumber.getText()+""+capacity);
        waitListCapacityNumber.setText(waitListCapacityNumber.getText()+""+waitListCapacity);
        availableNumber.setText(availableNumber.getText()+""+available);



        return convertView;
    }
}
