package com.group_15.bta.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class UserTest {

 @Test
    public void testUsers(){
     User user;
     user = new User();
     assertNotNull(user);
     user = new User("101", "1001");
     assertNotNull(user);
     assertEquals("101", user.getID());
     assertEquals("1001", user.getPassword());

     User user2;
     user2 = new User("102", "1002", "Sara");
     assertNotNull(user2);
     assertEquals("102", user2.getID());
     assertEquals("1002", user2.getPassword());
     assertEquals("Sara", user2.getName());

     assertTrue(user.equals(user));
     assertTrue(user2.equals(user2));
     assertFalse(user.equals(user2));
    }
}
