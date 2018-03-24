package com.thedionisio.model.ctrl;

import com.thedionisio.dao.CompanyRepository;
import com.thedionisio.model.bss.CompanyBss;
import com.thedionisio.model.dto.Company;
import com.thedionisio.util.verification.Validation;
import org.bson.Document;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by jonathan on 4/9/17.
 */
public class CompanyCtrl {

    private CompanyBss companyBss = new CompanyBss();
    private CompanyRepository companyRepository = new CompanyRepository();
    private final String collection  ="company";

    public Object create(Company company){

        try
        {
            if(company.createValidation())
            {
                if (companyBss.existingValidation(company))
                {
                    ResponseEntity responseEntity = companyRepository.create(collection, company.treatCreate());
                    try
                    {
                        if (Boolean.parseBoolean(responseEntity.getBody().toString()))
                        {
                            List<Company> companies = (List<Company>) companyRepository.findByEmail(company.email);
                            return Validation.resquest.REGISTRY_CREATE(companies.get(0).treatResponse());
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getCause());
                        return responseEntity;
                    }

                }
                return Validation.resquest.REGISTRY_EXISTED(company.attributeIdentifier() + company.email);
            }
            return Validation.resquest.NOT_CONTAINS_FIELDS(company.isRequired());
        }
        catch (Exception e)
        {
            return Validation.resquest.NOT_DATA_BASE();
        }

    }

    public Object find(){
        Object objectFind  = companyRepository.find(collection,new Company(), new Document(), new Document(),0);
        try
        {
            List<Company> companies = (List<Company>) objectFind;
            return companyBss.treatResponse(companies);
        }
        catch (Exception e)
        {
            return objectFind;
        }
    }

    public Object findOne(Object id){
        Object objectFind  = companyRepository.findOne(collection,id,new Company());
        try
        {
            List<Company> companies = (List<Company>) objectFind;
            return companies.get(0).treatResponse();
        }
        catch (Exception e)
        {
            return objectFind;
        }
    }

    public Object update(Company company){

        if (company.updateValidation())
        {
            Object objectUpdate = companyRepository.update(company.treatUpdate(),company._id,collection);
            try
            {
                List<Company> companyUpdate = (List<Company>) objectUpdate;
                return Validation.resquest.REGISTRY_UPDATE(companyUpdate.get(0).treatResponse());
            }
            catch (Exception e)
            {
                return objectUpdate;
            }

        }
        return Validation.resquest.CONTAINS_FIELDS_IMMUTABLE(company.isImmutable());

    }

    public Object removeOne(Company company){

        return  companyRepository.removeOne(company._id,collection);
    }



}
