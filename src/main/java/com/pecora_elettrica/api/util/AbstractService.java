package com.pecora_elettrica.api.util;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractService<E, ID> implements BaseService<E, ID> {

	protected abstract CrudRepository<E, ID> getRepository();

	@Override
	public ServiceResult<E> getById(ID id) {
		try {
			return ServiceResult.from(getRepository().findById(id));
		} catch (Exception ex) {
			return ServiceResult.submitFailure(ex);
		}
	}

	@Override
	public ServiceResult<Collection<E>> getAll() {
		Collection<E> rows = new LinkedList<>();
		getRepository().findAll().forEach(rows::add);

		return ServiceResult.submitSuccess(rows);
	}
}
