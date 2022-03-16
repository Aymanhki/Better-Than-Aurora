package com.group_15.bta.persistence;


import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public interface StudentSectionPersistence {

    ArrayList<StudentSection> getSectionList();
    void insertSection(StudentSection currentSection);

    void updateStudentSection(StudentSection currentSection);

    void deleteSection(StudentSection toRemove);

}
