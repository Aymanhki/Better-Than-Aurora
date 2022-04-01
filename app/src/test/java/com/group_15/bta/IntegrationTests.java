package com.group_15.bta;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.group_15.bta.business.AccessCategoriesIT;
import com.group_15.bta.business.AccessCoursesIT;
import com.group_15.bta.business.AccessSectionsIT;
import com.group_15.bta.business.AccessStudentSectionsIT;
import com.group_15.bta.business.AccessStudentsIT;
import com.group_15.bta.business.AccessUsers;
import com.group_15.bta.business.AccessUsersIT;
import com.group_15.bta.business.CalculateTestIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessCategoriesIT.class,
        AccessCoursesIT.class,
        AccessSectionsIT.class,
        AccessStudentSectionsIT.class,
        AccessStudentsIT.class,
        AccessUsersIT.class,
        CalculateTestIT.class
})
public class IntegrationTests {
}
