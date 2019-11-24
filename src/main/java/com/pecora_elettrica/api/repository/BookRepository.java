package com.pecora_elettrica.api.repository;

import com.pecora_elettrica.api.model.EBook;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<EBook, String> {

}
