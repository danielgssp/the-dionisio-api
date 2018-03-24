package com.thedionisio.resources;

import com.thedionisio.model.ctrl.CompanyCtrl;
import com.thedionisio.model.dto.Company;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jonathan on 4/9/17.
 */

@RestController
@RequestMapping(value = "/company")
public class CompanyResource {

    private CompanyCtrl companyCtrl = new CompanyCtrl();

    @RequestMapping(method = RequestMethod.GET)
    public Object get()  {return companyCtrl.find();}

    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    public Object getOne(@PathVariable Object id)  {
        return companyCtrl.findOne(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public Object create(@RequestBody Company company)  {

        return this.companyCtrl.create(company);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody Company company) {

        return this.companyCtrl.update(company);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Object removeOne(@RequestBody Company company) {

        return companyCtrl.removeOne(company);

    }
}
