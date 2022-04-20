package com.group_15.bta.objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AdvisorTest {
        @Test
        public void testAdvisor1()
        {
            Advisor advisor;
            Advisor advisor2;

            System.out.println("\nStarting testAdvisor");

            advisor = new Advisor("Sara","1234");
            assertNotNull(advisor);
            assertEquals("Sara",advisor.getID());
            assertEquals("1234", advisor.getPassword());

            advisor2 = new Advisor("123", "111", "Misa");
            assertEquals("123", advisor2.getID());
            assertEquals("Misa",advisor2.getName());
            assertEquals("111", advisor2.getPassword());

            assertTrue(advisor.equals(advisor));
            assertFalse(advisor.equals(advisor2));

            System.out.println("Finished testAdvisor");
        }
    }

