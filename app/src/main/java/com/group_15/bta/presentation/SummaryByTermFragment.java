package com.group_15.bta.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SummaryByTermFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SummaryByTermFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Student currentUser = (Student) new AccessUsers().getCurrentUser();
    private double tuition=0.0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //private Student currentUser = (Student) new AccessUsers().getCurrentUser();
    public SummaryByTermFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment summary_by_term.
     */
    // TODO: Rename and change types and number of parameters
    public static SummaryByTermFragment newInstance(String param1, String param2) {
        SummaryByTermFragment fragment = new SummaryByTermFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((StudentAccountActivity) getActivity()).setActionBarTitle("Account Summary");


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root=inflater.inflate(R.layout.fragment_summary_by_term, container, false);
        TextView tuition_fees=(TextView)root.findViewById(R.id.tuition_fee_amount);
        ArrayList<StudentSection> studentCurrentSections = currentUser.getStudentSections(true);
        Log.d("number of courses",""+studentCurrentSections.size());
        for (int i = 0; i <studentCurrentSections.size() ; i++) {
            tuition+=studentCurrentSections.get(i).getAssociatedCourse().getTuition();

            Log.d("course"+i,studentCurrentSections.get(i).getAssociatedCourse().getTitle()+tuition);
        }
        tuition_fees.setText(String.format("\n$%s", tuition));
        TextView total_fees=(TextView) root.findViewById(R.id.total_fee_amount);
        TextView total_payable=(TextView) root.findViewById(R.id.total_payable_amount);
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