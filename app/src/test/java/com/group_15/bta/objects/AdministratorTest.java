package com.group_15.bta.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AdministratorTest {
        @Test
public void testAdmin1() {
            Administrator admin;
            Administrator admin2;

            System.out.println("\nStarting testAdmin");

            admin=new Administrator();
            assertNotNull(admin);

            admin = new Administrator("Sara", "1234");
            assertNotNull(admin);
            assertEquals("Sara", admin.getID());
            assertEquals("1234", admin.getPassword());

            admin2 = new Administrator("123", "111", "Misa");
            assertEquals("123", admin2.getID());
            assertEquals("Misa", admin2.getName());
            assertEquals("111", admin2.getPassword());

            assertTrue(admin.equals(admin));
            assertFalse(admin.equals(admin2));

            System.out.println("Finished testAdmin");
        }
}

