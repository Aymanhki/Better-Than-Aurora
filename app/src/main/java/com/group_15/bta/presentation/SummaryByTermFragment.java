package com.group_15.bta.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Student;

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
        String course="";
        currentUser.g
        Log.d("got until courseName",course);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary_by_term, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}