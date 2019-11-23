package com.pecora_elettrica.api.util;

import java.util.Collection;

public interface BaseService<E, ID> {

	ServiceResult<E> getById(ID id);

	ServiceResult<Collection<E>> getAll();
}
