package com.thedionisio.model.ctrl;

import com.thedionisio.dao.CompanyRepository;
import com.thedionisio.dao.PersonRepository;
import com.thedionisio.model.bss.CommonPersonCompanyBss;
import com.thedionisio.model.dto.Company;
import com.thedionisio.model.dto.ForgotPassword;
import com.thedionisio.model.dto.Person;
import com.thedionisio.services.security.Security;

import java.time.LocalDateTime;

/**
 * Created by jonathan on 6/1/17.
 */
public class ForgotPasswordCtrl {
    private CommonPersonCompanyBss commonPersonCompanyBss = new CommonPersonCompanyBss();
    private PersonRepository personRepository = new PersonRepository();
    private CompanyRepository companyRepository = new CompanyRepository();

    private String collectionP = "person";
    private String collectionC = "company";

    public Object getPassword(String email)
    {
        Person person = null;
        Company company = null;
        Object response =  commonPersonCompanyBss.findPersonOrCompanyTreat(email);

        try
        {
            person = (Person) response;
        }
        catch (Exception e)
        {
            company = (Company) response;
        }

        if (person != null)
        {
            String hash = Security.encryption.generateHash(person.email+ LocalDateTime.now());
            person.password = Security.encryption.generateHash(hash);
            personRepository.update(person,person._id,collectionP);
            return person.email+"/"+hash;
        }
        else if (company != null)
        {
            String hash = Security.encryption.generateHash(company.email+ LocalDateTime.now());
            company.password = Security.encryption.generateHash(hash);
            companyRepository.update(company,company._id,collectionC);
            return company.email+"/"+hash;
        }
        else
        {
            return response;
        }

    }

    public Object setPassword(ForgotPassword forgotPassword)
    {
        Person person = null;
        Company company = null;
        Object response =  commonPersonCompanyBss.findPersonOrCompany(forgotPassword.email);

        try
        {
            person = (Person) response;
        }
        catch (Exception e)
        {
            company = (Company) response;
        }
        if (person!=null && (Security.encryption.isPasswordValid(person.password,forgotPassword.oldPassword)))
        {
            person.password = Security.encryption.generateHash(forgotPassword.newPassword);
            personRepository.update(person,person._id,collectionP);
            return true;
        }
        else if (company!=null && (Security.encryption.isPasswordValid(company.password,forgotPassword.oldPassword)))
        {
            company.password = Security.encryption.generateHash(forgotPassword.newPassword);
            companyRepository.update(company,company._id,collectionC);
            return true;
        }
        return false;

    }






}
