Worksheet 2

Paying off technical debt

Few similar scenarios of techinical debt speicifially with the databases. 
All of them were Delibrate and Prudent debt. Changing them from CoursesList and SectionsList to Access___ to better match whats needed for database. We purposefully used the name 
__ List although we knew it would eventually need to change, so deliberately. We knew things would change for the database as well so we knew the fix would come later, ie predunt. 
Similar things were done accross the project.

Initial Changes:
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/af9e4fa786fda8c9107284f81ee6c966ae15e996/app/src/main/java/com/group_15/bta/business/AccessCourses.java
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/af9e4fa786fda8c9107284f81ee6c966ae15e996/app/src/main/java/com/group_15/bta/business/AccessSections.java


Current: https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/10e31c7a25b1ea4a26e5e8ae2f66a66e8c995872/app/src/main/java/com/group_15/bta/business/AccessCourses.java
Current: https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/10e31c7a25b1ea4a26e5e8ae2f66a66e8c995872/app/src/main/java/com/group_15/bta/business/AccessSections.java


A form of Inadvertent and Prudent was now realizing we should have used Enums for our Days of the week, as the marker mentioned . Enums would limit what options there were and better way to represent the days when classes can happen.
It was reckless due to the fact no consideration of enums was taken, due to the fact we have limited experience. Prudent now realizing how much it improves it. 

https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/10e31c7a25b1ea4a26e5e8ae2f66a66e8c995872/app/src/main/java/com/group_15/bta/objects/Section.java

SOLID

https://code.cs.umanitoba.ca/winter-2022-a01/group-14/personal-fitness-trainer/-/issues/37
Interface segregation principle violated in Profile Fragment. Should separate Account details from User details.


Retrospective

We had realized somethings had taken more time to do things was the primary changes we made was time related.
For example: https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/11

We took more time to discuss things amongst each other communicating a bit more between each other. No real way to show evidence of that with a link.


Design patterns

Adapter, we adapt sections into student sectoins for the student side. 


Chain of Responsibility, with the Students, Instructor, Administrator and Student Sections. Each user has their own responsibility according to what their user type is.
Students can register for sections, Administrator can create sections and set them up, Instructors can change the grade accordingly. 



Iteration 1 feeback
Had a naming issue. Courses described one course. Was noted by the grader issue was fixed and now it is just Course. 
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/10e31c7a25b1ea4a26e5e8ae2f66a66e8c995872/app/src/main/java/com/group_15/bta/objects/Course.java

Also updated days, as previously mentioned, to use enums. 
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/10e31c7a25b1ea4a26e5e8ae2f66a66e8c995872/app/src/main/java/com/group_15/bta/objects/Section.java

The issue created before we spoke to Dr. Matheson about it and decision was made that it was not a SOLID violation.
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/37
