package com.myorg.employee.util;

import org.junit.Test;

import static com.myorg.employee.util.PasswordHash.hashed;
import static org.junit.Assert.assertEquals;

public class PasswordHashTest {
   @Test
   public void testMD5Hash() {
       assertEquals("5f4dcc3b5aa765d61d8327deb882cf99", hashed("password"));
   }
}
