What technical debt has been cleaned up
========================================

Show links to a commit where you paid off technical debt. Write 2-5 sentences
that explain what debt was paid, and what its classification is.

Primary sources of our debt that were paid off would be the bugs we had to fix. There were a few bugs that we caught and still released
due to time constraints. The type of debt was Reckless and Inadvertent, primarly since we are learning Android Studio for the first time
and thats what bugs really are, things not working as you intended them to work. 

What technical debt did you leave?
==================================

What one item would you like to fix, and can't? Anything you write will not
be marked negatively. Classify this debt.

More bugs primarily were left, Database connection problems, wish it was online to be able to close sessions.
Again as question 1 stated these would be Reckless and Inadvertent. 

Discuss a Feature or User Story that was cut/re-prioritized
============================================

When did you change the priority of a Feature or User Story? Why was it
re-prioritized? Provide a link to the Feature or User Story. This can be from any
iteration.

Change the priority of Payment processing to be none existant. The whole feature seemed unreasonable for us to do since we are not looking to take
any real payments. 

https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/22

Acceptance test/end-to-end
==========================

Write a discussion about an end-to-end test that you wrote. What did you test,
how did you set up the test so it was not flaky? Provide a link to that test.

CreateStudent was one test we created. Sara had written the test, once we divided up the tasks. In order to avoid flakiness Sara deleted any "student3" that may
have existed before the test begins, to avoid problems.

https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/blob/main/app/src/androidTest/java/com/group_15/bta/CreateStudentTest.java

Acceptance test, untestable
===============

What challenges did you face when creating acceptance tests? What was difficult
or impossible to test?

"Nightmares" - Ayman, We had difficulty getting espresso doing exactly what we wanted it to do. It's impossilbe to test for some bugs,
some bugs we had like when the project was running for a long time we would have it crash or if we were switching users it could also sometimes crash and sometimes it won't. 
Stuff thats hard to repeat in a test. 

Velocity/teamwork
=================

Did your estimates get better or worse through the course? Show some
evidence of the estimates/actuals from tasks.

Between Iteration 1 and 2 our time estimates improved greatly, that is visible even on our graph. Beyond that as well we updated estimates on some user
stories and the related feature. 

For iteration 3, we were left only with the low and medium priority tasks that we were unable to get to in previous iterations.
We completed a couple of the user stories, but decided to leave the rest for a future iteration.
