package com.group_15.bta.business;

import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class Calculate
{
	public static StudentSection.gpa gpa(ArrayList<StudentSection> studentSections)
	{
		StudentSection.grades[] grades = StudentSection.grades.values();
		StudentSection.gpa[] values = StudentSection.gpa.values();

		StudentSection studentSection;
		StudentSection.gpa gpa = null;
		StudentSection.grades grade;
		double gradeTotal;
		int studentSectionCount;
		int validGrades;
		int totalCredit;
		int missingGrades;
		int count;
		boolean found;

		validGrades = 0;
		missingGrades = 0;
		gradeTotal = 0;
		totalCredit = 0;

		if ((studentSections!=null) && (studentSections.size()>0))
		{
			for (studentSectionCount=0; studentSectionCount<studentSections.size(); studentSectionCount++)
			{
				if (studentSections.get(studentSectionCount) == null)
				{	// Invalid or null element
					missingGrades = 0;
					validGrades = 0;
					studentSectionCount = studentSections.size()+1;
					gpa = StudentSection.gpa.invalid;
				}
				else
				{
					studentSection = studentSections.get(studentSectionCount);
					grade = studentSection.getGrade();
					found = false;
					if (grade.equals(StudentSection.grades.IP))
					{	// found a course in progress, no grade yet
						missingGrades++;
					}
					else
					{

						for (count=0; count<grades.length && !found; count++)
						{
							if (grades[count].equals(grade))
							{
								found = true;
								double credit = studentSection.getCreditHours();
								gradeTotal += values[count].getGpa()*credit;
								validGrades ++;
								totalCredit += credit;
							}
						}
					}
				}
			}
			if (((validGrades+missingGrades)==studentSections.size()) && (validGrades>0))
			{
				gpa = StudentSection.gpa.getGpa(gradeTotal/totalCredit);
			}
			else if (missingGrades != studentSections.size())
			{	// Invalid grade
				gpa = StudentSection.gpa.invalid;
			}
		}

		return gpa;
	}

	public static String creditHours(ArrayList<StudentSection> studentSections)
	{

		Double sum = 0.0;

		for(int i=0; i<studentSections.size(); i++)
		{
			sum += studentSections.get(i).getCreditHours();
		}

		return String.valueOf(sum);
	}
}
