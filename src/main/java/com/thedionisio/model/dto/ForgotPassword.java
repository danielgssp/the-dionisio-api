package com.thedionisio.model.dto;

/**
 * Created by jonathan on 6/1/17.
 */
public class ForgotPassword {
    @Override
    public String toString() {
        return "ForgotPassword{" +
                "email='" + email + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

    public String email;
    public String oldPassword;
    public String newPassword;
}
