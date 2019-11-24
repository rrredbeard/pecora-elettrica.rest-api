package com.pecora_elettrica.api.facade;

import com.pecora_elettrica.api.dto.BookDTO;
import com.pecora_elettrica.api.model.EBook;
import com.pecora_elettrica.api.service.BookService;
import com.pecora_elettrica.api.util.AbstractFacade;
import com.pecora_elettrica.api.util.BaseService;
import com.pecora_elettrica.api.util.ServiceResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDTOFacade extends AbstractFacade<EBook, String, BookDTO> {

    @Autowired
    private BookService service;

    @Override
    protected Class<BookDTO> getDtoClass() {
        return BookDTO.class;
    }

    @Override
    protected BaseService<EBook, String> getService() {
        return service;
    }

    @Override
    public ServiceResult<EBook> convertToEntity(BookDTO dto) {
        return service.getById(dto.getIsbn());
    }

}
