package com.thedionisio.util;

import com.thedionisio.services.security.Security;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jonathan on 3/19/17.
 */
public class EncryptionTest {


    @Test
    public void encryptionTest(){

        assertEquals("7c4a8d09ca3762af61e59520943dc26494f8941b",
                      Security.encryption.generateHash("123456"));

    }

    @Test
    public void isValid()
    {
        assertEquals(true,
                      Security.encryption.isPasswordValid(Security.encryption.generateHash("123456"),
                                                                               "123456"));
    }


}
