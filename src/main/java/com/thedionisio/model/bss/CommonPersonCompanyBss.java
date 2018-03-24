package com.thedionisio.model.bss;

import com.thedionisio.dao.CompanyRepository;
import com.thedionisio.dao.PersonRepository;
import com.thedionisio.model.dto.Company;
import com.thedionisio.model.dto.Person;

import java.util.List;

/**
 * Created by jonathan on 5/20/17.
 */
public class CommonPersonCompanyBss {

    private PersonRepository personRepository = new PersonRepository();
    private CompanyRepository companyRepository = new CompanyRepository();

    public Boolean existingValidation(String email){
        try
        {
            List listPerson = (List) personRepository.findByEmail(email);
            List listCompay = (List) companyRepository.findByEmail(email);
            return listPerson.size() <= 0 && listCompay.size() <= 0 ;
        }
        catch (Exception e )
        {
            return null;
        }

    }

    public Object findPersonOrCompanyTreat(String email){
        try
        {
            List listPerson = (List) personRepository.findByEmail(email);
            List listCompany = (List) companyRepository.findByEmail(email);
            if (listPerson.size() > 0) return ((Person)listPerson.get(0)).treatResponse();
            if (listCompany.size() > 0) return((Company)listCompany.get(0)).treatResponse();
            return null;
        }
        catch (Exception e )
        {
            return null;
        }

    }

    public Object findPersonOrCompany(String email){
        try
        {
            List listPerson = (List) personRepository.findByEmail(email);
            List listCompany = (List) companyRepository.findByEmail(email);
            if (listPerson.size() > 0) return ((Person)listPerson.get(0));
            if (listCompany.size() > 0) return((Company)listCompany.get(0));
            return null;
        }
        catch (Exception e )
        {
            return null;
        }

    }
}
