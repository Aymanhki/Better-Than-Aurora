package com.group_15.bta.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.group_15.bta.persistence.IDataReceiver;
import com.group_15.bta.R;
import com.group_15.bta.objects.SectionListAdapter;
import com.group_15.bta.objects.Student;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IDataReceiver userReceiver;
    private Student currentUser;
    private ListView enrolledSectionsList;
    private SectionListAdapter enrolledSectionsAdapted;

    public StudentHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment student_home.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentHomeFragment newInstance(String param1, String param2) {
        StudentHomeFragment fragment = new StudentHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentUser = ((StudentAccountActivity)getActivity()).getData();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        if(StudentHomeFragmentArgs.fromBundle(getArguments()).getSection() != null)
//        {
//            Section addedSection = StudentHomeFragmentArgs.fromBundle(getArguments()).getSection();
//            currentUser.addSection(addedSection);
//        }

        enrolledSectionsAdapted = new SectionListAdapter(getContext(), R.id.section_name_list_item, currentUser.getEnrolledSections());
        enrolledSectionsList = view.findViewById(R.id.student_enrolled_courses);
        enrolledSectionsList.setAdapter(enrolledSectionsAdapted);
        NavController navController = NavHostFragment.findNavController(this);


    }
}