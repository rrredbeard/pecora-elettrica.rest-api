package com.pecora_elettrica.api.service;

import com.pecora_elettrica.api.model.EBook;
import com.pecora_elettrica.api.repository.BookRepository;
import com.pecora_elettrica.api.util.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractService<EBook, String> {

    @Override
    protected CrudRepository<EBook, String> getRepository() {
        return bookRepo;
    }

    /** **/

    @Autowired
    private BookRepository bookRepo;

    // @Autowired private BookCopyRepository copiesRepo;

}