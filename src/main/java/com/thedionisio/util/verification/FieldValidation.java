package com.thedionisio.util.verification;

/**
 * Created by jonathan on 6/13/17.
 */
public class FieldValidation {

    public boolean isValidEmail(String email) {
        char [] emailChar = email.toCharArray();
        return  email.contains("@") &&
                !email.contains(" ") &&
                emailChar[0]!='@' &&
                emailChar[emailChar.length-1]!='@' &&
                emailChar.length >3;
    }
}
