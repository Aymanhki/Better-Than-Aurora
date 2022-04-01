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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.SectionListAdapter;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSectionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView confirmSectionList;
    private SectionListAdapter sectionsAdapted;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddSectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_a_course_with_section_confirmation.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSectionFragment newInstance(String param1, String param2) {
        AddSectionFragment fragment = new AddSectionFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_a_course_with_section_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Section selectedSection;
        selectedSection = AddSectionFragmentArgs.fromBundle(requireArguments()).getSection();
        confirmSectionList = view.findViewById(R.id.confirm_section_selection_list);
        ArrayList<Section> selectedSections = new ArrayList<>();
        selectedSections.add(selectedSection);
        sectionsAdapted = new SectionListAdapter(getContext(), R.layout.section_list_item, selectedSections);
        confirmSectionList.setAdapter(sectionsAdapted);
        Student currentUser = (Student) new AccessUsers().getCurrentUser();


        NavController navController = NavHostFragment.findNavController(this);
        Button addSectionBtn = (Button) view.findViewById(R.id.add_section_btn);
        addSectionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentUser.getEnrolledSections().contains(new StudentSection(currentUser.getID(), "In Progress", selectedSection,  new AccessCourses().getCourse(selectedSection.getAssociatedCourse())))) {
                    if(currentUser.getEnrolledSections().size()<currentUser.getMAX_CLASSES()) {
                        currentUser.addSection(new StudentSection(currentUser.getID(), "In Progress", selectedSection, new AccessCourses().getCourse(selectedSection.getAssociatedCourse())));
                    }
                    else
                    {
                        Toast.makeText(getContext(), "You are already enrolled in the maximum number of classes", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "You are already enrolled in this section", Toast.LENGTH_LONG).show();
                }
                navController.navigate(R.id.action_add_a_course_with_section_confirmation_to_student_home);
            }
        });
    }
}