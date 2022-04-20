package com.group_15.bta.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class SummaryByTermFragment extends Fragment {


    private final Student currentUser = (Student) new AccessUsers().getCurrentUser();
    private double tuition=0.0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_summary_by_term, container, false);
        TextView tuition_fees= root.findViewById(R.id.tuition_fee_amount);
        ArrayList<StudentSection> studentCurrentSections = currentUser.getStudentSections(true);
        Log.d("number of courses",""+studentCurrentSections.size());
        for (int i = 0; i <studentCurrentSections.size() ; i++) {
            tuition+=studentCurrentSections.get(i).getAssociatedCourse().getTuition();

            Log.d("course"+i,studentCurrentSections.get(i).getAssociatedCourse().getTitle()+tuition);
        }
        tuition_fees.setText(String.format("\n$%s", tuition));
        TextView total_fees= root.findViewById(R.id.total_fee_amount);
        TextView total_payable=  root.findViewById(R.id.total_payable_amount);
        double endowment_fee=39.00;
        double library_fee=24.57;
        double sport_fee=93.26;
        double tech_fee=80.00;
        double term_charges=tuition+endowment_fee+library_fee+sport_fee+tech_fee;
        total_fees.setText(String.format("$%s\n",term_charges));
        total_payable.setText(String.format("\n$%s\n",term_charges));
        return root;




    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}