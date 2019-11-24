package com.pecora_elettrica.api.repository;

import com.pecora_elettrica.api.model.EBookCopy;

import org.springframework.data.repository.CrudRepository;

public interface BookCopyRepository extends CrudRepository<EBookCopy, EBookCopy.PKey> {

}
