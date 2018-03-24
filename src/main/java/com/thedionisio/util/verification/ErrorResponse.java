package com.thedionisio.util.verification;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jonathan on 5/2/17.
 */

@RestController()
@RequestMapping(value = "/error")
public class ErrorResponse {

    @RequestMapping(value = "/401", method = RequestMethod.GET)
    public Object error401()
    {
      return   Validation.resquest.REQUEST_NOT_AUTHORIZED();
    }
}
