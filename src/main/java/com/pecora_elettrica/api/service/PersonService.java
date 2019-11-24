package com.pecora_elettrica.api.service;

import com.pecora_elettrica.api.model.EPerson;
import com.pecora_elettrica.api.repository.PersonRepository;
import com.pecora_elettrica.api.util.AbstractService;
import com.pecora_elettrica.api.util.ServiceResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends AbstractService<EPerson, Long> {

    @Autowired
    private PersonRepository repo;

    @Override
    protected CrudRepository<EPerson, Long> getRepository() {
        return repo;
    }

    public ServiceResult<EPerson> addNewUser(EPerson p) {
        try {
            return ServiceResult.submitSuccess(repo.save(p));
        } catch (Exception ex) {
            return ServiceResult.submitFailure(ex);
        }
    }

}
