package com.pecora_elettrica.api.facade;

import javax.naming.OperationNotSupportedException;

import com.pecora_elettrica.api.dto.PersonDTO;
import com.pecora_elettrica.api.model.EPerson;
import com.pecora_elettrica.api.service.PersonService;
import com.pecora_elettrica.api.util.AbstractFacade;
import com.pecora_elettrica.api.util.BaseService;
import com.pecora_elettrica.api.util.ServiceResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDTOFacade extends AbstractFacade<EPerson, Long, PersonDTO> {

    @Autowired
    private PersonService service;

    @Override
    protected Class<PersonDTO> getDtoClass() {
        return PersonDTO.class;
    }

    @Override
    protected BaseService<EPerson, Long> getService() {
        return service;
    }

    @Override
    public ServiceResult<EPerson> convertToEntity(PersonDTO dto) {
        return ServiceResult.submitFailure(new OperationNotSupportedException());
    }

    /** others */

    public ServiceResult<EPerson> getCurrentUser() {
        // TODO: support for multi user
        return service.getById(0L);
    }

    public ServiceResult<EPerson> saveNewUser(PersonDTO dto) {
        EPerson p = EPerson.builder().firstName(dto.getFirstName()).lastName(dto.getLastName()).build();

        return service.addNewUser(p);
    }
}