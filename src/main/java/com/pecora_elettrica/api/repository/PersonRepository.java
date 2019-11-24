package com.pecora_elettrica.api.repository;

import com.pecora_elettrica.api.model.EPerson;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<EPerson, Long> {

}
