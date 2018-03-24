package com.thedionisio.services.security.service;

import com.thedionisio.dao.CompanyRepository;
import com.thedionisio.dao.PersonRepository;
import com.thedionisio.model.dto.Company;
import com.thedionisio.model.dto.Person;
import com.thedionisio.services.security.model.SpringSecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = null;
        Company company = null;

        try
        {
            List<Person> persons = (List<Person>) new PersonRepository().findByEmail(email);
            person = persons.get(0);
        }
        catch (Exception e)
        {
            System.out.println("log de erro de pessoa");
        }

        try
        {
            List<Company> companie = (List<Company>) new CompanyRepository().findByEmail(email);
            company = companie.get(0);
        }
        catch (Exception e)
        {
            System.out.println("log de erro de companie");
        }

        if (person != null)
        {
            try
            {
                return new SpringSecurityUser(
                        person._id,
                        person.email,
                        person.BCryptEncoderPassword(),
                        person.name,
                        null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN")
                );
            }
            catch (Exception e )
            {
                System.out.println(e.getCause());
                return null;
            }
        }
        else if (company != null)
        {
            try
            {
                return new SpringSecurityUser(
                        company._id,
                        company.email,
                        company.BCryptEncoderPassword(),
                        company.name,
                        null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN")
                );
            }
            catch (Exception e )
            {
                System.out.println(e.getCause());
                return null;
            }
        }
        else
        {
            throw new UsernameNotFoundException(String.format("No appUser found with email '%s'.", email));
        }
    }

}
