Iteration 1 Worksheet
=====================

Adding a feature
-----------------

Tell the story of how one of your features was added to the project.
Provide links to the
feature, user stories, and merge requests (if used), associated tests, and merge commit
that was used complete the feature.

Use one or two paragraphs which can have point-form within them.

We started off discussing the layout, we discussed the basic needs for our program. 
The discussion of adding and deleting courses came up eventually. 
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/17
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/4
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/6
The feature was implemented,using a fake database.
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/merge_requests/8
Here is the first merge request involving it.
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/merge_requests/11
Here another merge request to update it.  


Exceptional code
----------------

Provide a link to a test of exceptional code. In a few sentences,
provide an explanation of why the exception is handled or thrown
in the code you are testing.

Our code currently does not throw any exceptions, given that the communication between data layers is incomplete there is no room for custom input currently, however planned for in the future, therefore there is no reason to throw exception. 

Branching
----------

Provide a link to where you describe your branching strategy.

Provide screen shot of a feature being added using your branching strategy
successfully. The [GitLab Graph tool can do this](https://code.cs.umanitoba.ca/comp3350-summer2019/cook-eBook/-/network/develop),
as well as using `git log --graph`.

Here we described our branching strategy:
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/commit/24a1cb176cd725f8afbd55ad747a6c1908e0e117

Here is a quick merge as we had made some small changes and immediately updated it. 

|\  Merge: ab3b6b9 d3a5ffc

| | Author: Alexander Martchenko <martchea@myumanitoba.ca>

| | Date:   Thu Mar 3 21:30:09 2022 +0000

| |

| |     Merge branch 'updateAdminCourses' into 'main'

| |     

| |     updated admin courses, reorganized the items into files

| |     

| |     See merge request winter-2022-a01/group-15/better-than-aurora-registration-system-a01!11

| |

| * commit d3a5ffc3faeffaaa7b8af72e234dd54ec96a83ee

|/  Author: martchea <martchea@myumanitoba.ca>

|   Date:   Thu Mar 3 15:26:54 2022 -0500

SOLID
-----

Find a SOLID violation in the project of group `(n%16)+1` (group 16 does group 1).
Open an issue in their project with the violation,
clearly explaining the SOLID violation - specifying the type, provide a link to that issue. Be sure
your links in the issues are to **specific commits** (not to `main`, or `develop` as those will be changed).

Provide a link to the issue you created here.

https://code.cs.umanitoba.ca/winter-2022-a01/group-16/killer-nano-robots/-/issues/23

Agile Planning
--------------

Write a paragraph about any plans that were changed. Did you
'push' any features to iteration 2? Did you change the description
of any Features or User Stories? Have links to any changed or pushed Features
or User Stories.

We pushed several of our stories due to incomplete user stories. 
For Course Detail Management
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/24
We pushed user story:
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/10
to iteration 2. 

For Student Active Registration Management
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/2
We pushed user stories:
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/14
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/12
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/7
to iteration 2.

For Student Account Management:
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/3
we pushed user stories:
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/9
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/13
to iteration 2.
 
Added some user stories as well as we created:
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/36
https://code.cs.umanitoba.ca/winter-2022-a01/group-15/better-than-aurora-registration-system-a01/-/issues/35
