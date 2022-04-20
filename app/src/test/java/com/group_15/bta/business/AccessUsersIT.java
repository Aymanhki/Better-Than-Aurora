package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testGetUsers() {
        final ArrayList<User> users = accessUsers.getUsers();
        assertEquals(16, users.size());
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }
}
