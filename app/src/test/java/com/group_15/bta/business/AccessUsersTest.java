package com.group_15.bta.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group_15.bta.objects.User;
import com.group_15.bta.persistence.UserPersistence;

import org.junit.Before;
import org.junit.Test;

public class AccessUsersTest
{
    private AccessUsers accessUsers;
    private UserPersistence userPersistence;

    @Before
    public void setup()
    {
        userPersistence = mock(UserPersistence.class);
        accessUsers = new AccessUsers(userPersistence);
    }

    @Test
    public void validateLoginAttemptTest()
    {
        final boolean valid;
        when(userPersistence.validateLoginAttempt("Ayman", "Ayman")).thenReturn(true);
        valid = accessUsers.validateLoginAttempt("Ayman", "Ayman");
        assert valid;
        verify(userPersistence).validateLoginAttempt("Ayman", "Ayman");
    }

    @Test
    public void getUsersTest()
    {
        final User user;
        when(userPersistence.getUser("Ayman", "Ayman")).thenReturn(new User("Test", "Test", "Test"));
        user = accessUsers.getUser("Ayman", "Ayman");
        assertNotNull(user);
        assertEquals(user.getName(), "Test");
        assertEquals(user.getID(), "Test");
        assertEquals(user.getPassword(), "Test");
        verify(userPersistence).getUser("Ayman", "Ayman");
    }

}
