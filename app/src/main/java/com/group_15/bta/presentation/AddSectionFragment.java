package com.group_15.bta.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.group_15.bta.R;
import com.group_15.bta.business.AccessCourses;
import com.group_15.bta.business.AccessSections;
import com.group_15.bta.business.AccessStudentSections;
import com.group_15.bta.business.AccessStudents;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.objects.Section;
import com.group_15.bta.objects.SectionListAdapter;
import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class AddSectionFragment extends Fragment {


    private final AccessUsers usersPersistence = new AccessUsers();
    private final AccessStudentSections studentSectionsPersistence = new AccessStudentSections();
    private final AccessStudents studentsPersistence = new AccessStudents();
    private final AccessSections sectionsPersistence = new AccessSections();
    private final Student currentUser = (Student) usersPersistence.getCurrentUser();
    private final AccessCourses coursesPersistence = new AccessCourses();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        ListView confirmSectionList = view.findViewById(R.id.confirm_section_selection_list);
        ArrayList<Section> selectedSections = new ArrayList<>();
        selectedSections.add(selectedSection);
        SectionListAdapter sectionsAdapted = new SectionListAdapter(getContext(), R.layout.section_list_item, selectedSections);
        confirmSectionList.setAdapter(sectionsAdapted);
        NavController navController = NavHostFragment.findNavController(this);
        Button addSectionBtn = view.findViewById(R.id.add_section_btn);

        addSectionBtn.setOnClickListener(view1 -> {
            if (!studentSectionsPersistence.duplicate(currentUser, selectedSection)){
                if(!studentsPersistence.fullTime(currentUser)) {
                    if(!studentSectionsPersistence.overlaps(selectedSection))
                    {
                        if(selectedSection.availablePosition())
                        {
                            Section updatedSelectedSection = new Section(selectedSection.getSection(), selectedSection.getInstructor(), selectedSection.getDaysRaw(),
                                    selectedSection.getTime(), selectedSection.getLocation(), selectedSection.getAvailable()-1,
                                    selectedSection.getCAP(), selectedSection.getAssociatedCourse(), selectedSection.getAssociatedCategory());
                            sectionsPersistence.updateSection(updatedSelectedSection);
                            currentUser.addSection(new StudentSection(currentUser.getID(), StudentSection.grades.IP, updatedSelectedSection, coursesPersistence.getCourse(updatedSelectedSection.getAssociatedCourse())));
                            Toast.makeText(getContext(), "You are now enrolled in "+selectedSection.getSection(), Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "There is no room in "+selectedSection.getSection(), Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getContext(), "This section interferes with another one", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "You are already enrolled in the maximum number of classes", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(getContext(), "You are already enrolled in this course", Toast.LENGTH_LONG).show();
            }

            navController.navigate(R.id.action_add_a_course_with_section_confirmation_to_student_home);
        });
    }
}