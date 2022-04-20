package com.group_15.bta.business;

import com.group_15.bta.objects.StudentSection;

import java.util.ArrayList;

public class Calculate
{
	public static String gpa(ArrayList<StudentSection> studentSections)
	{
		final String[] grades = {"A+","A","B+","B","C+","C","D","F"};
		final double[] values = {4.5,4.0,3.5,3.0,2.5,2.0,1.0,0.0};
		StudentSection studentSection;
		String gpa;
		String grade;
		double gradeTotal;
		int studentSectionCount;
		int validGrades;
		int missingGrades;
		int count;
		boolean found;

		validGrades = 0;
		missingGrades = 0;
		gradeTotal = 0;
		gpa = " ";
		if ((studentSections!=null) && (studentSections.size()>0))
		{
			for (studentSectionCount=0; studentSectionCount<studentSections.size(); studentSectionCount++)
			{
				if (studentSections.get(studentSectionCount) == null)
				{	// Invalid or null element
					missingGrades = 0;
					validGrades = 0;
					studentSectionCount = studentSections.size()+1;
					gpa = "?";
				}
				else
				{
					studentSection = studentSections.get(studentSectionCount);
					grade = studentSection.getGrade();
					found = false;
					if (grade.trim().equals("In Progress"))
					{	// found a course in progress, no grade yet
						missingGrades++;
					}
					else
					{
						for (count=0; count<grades.length&&!found; count++)
						{
							if (grades[count].equals(grade))
							{
								found = true;
								gradeTotal += values[count];
								validGrades++;
							}
						}
					}
				}
			}
			if (((validGrades+missingGrades)==studentSections.size()) && (validGrades>0))
			{
				gpa = "" +(gradeTotal/validGrades);
			}
			else if (missingGrades != studentSections.size())
			{	// Invalid grade
				gpa = "?";
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
