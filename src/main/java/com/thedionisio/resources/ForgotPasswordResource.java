package com.thedionisio.resources;

import com.thedionisio.model.ctrl.ForgotPasswordCtrl;
import com.thedionisio.model.dto.ForgotPassword;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jonathan on 6/1/17.
 */

@RestController
public class ForgotPasswordResource {

    ForgotPasswordCtrl forgotPasswordCtrl = new ForgotPasswordCtrl();

    @RequestMapping(value = "forgot-password/{email:.+}", method = RequestMethod.GET)
    public Object getLinkForRest(@PathVariable String email)
    {

        return  forgotPasswordCtrl.getPassword(email);
    }

    @RequestMapping(value = "reset-password/{email:.+}/{hash:.+}", method = RequestMethod.GET)
    public Object getRestPassword(@PathVariable String email,
                                  @PathVariable String hash)
    {
        return  true;
    }

    @RequestMapping(value = "reset-password/{email:.+}/{hash:.+}", method = RequestMethod.POST)
    public Object setRestPassword(@PathVariable String email,
                                  @PathVariable String hash,
                                  @RequestBody ForgotPassword forgotPassword)
    {
        forgotPassword.email = email;
        forgotPassword.oldPassword = hash;
        return  forgotPasswordCtrl.setPassword(forgotPassword);
    }

}
