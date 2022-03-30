package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.group_15.bta.objects.Student;
import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.HSQLDB.UserPersistenceHSQLDB;
import com.group_15.bta.persistence.UserPersistence;
import com.group_15.bta.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AccessUsersIT {
    private AccessUsers accessUsers;
    private File tempDB;


    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        final UserPersistence persistence = new UserPersistenceHSQLDB(this.tempDB.getAbsolutePath().replace(".script", ""));
        this.accessUsers = new AccessUsers(persistence);
    }
    /*
        @Test
        public void testListUsers() {
            final Student user;

            user =(Student)accessUsers.getUsers().get(0);
            assertNotNull("first sequential course should not be null", user);
            assertTrue("Ayman".equals(user.getName()));

        }
    */
    @Test
    public void testGetUsers() {
        final ArrayList<User> users = accessUsers.getUsers();
        assertEquals(4, users.size());
    }
    /*
        @Test
        public void testDeleteUser() {
            final Student c =(Student) accessUsers.getUsers().get(0);
            ArrayList<User> users = accessUsers.getUsers();
            assertEquals(4, users.size());
            accessUsers.deleteUser(c);
            users = accessUsers.getUsers();
            assertEquals(3, users.size());
        }

        @Test
        public void testInsertUser() {
            final User c = new User("student2", "2", "Sara");
            accessUsers.insertUser(c);
            assertEquals(5, accessUsers.getUsers().size());
        }
    */
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
