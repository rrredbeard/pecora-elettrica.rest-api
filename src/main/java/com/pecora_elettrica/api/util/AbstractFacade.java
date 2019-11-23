package com.pecora_elettrica.api.util;

import java.util.Collection;

/**
 *
 * @param <E> source Entity
 * @param <ID> Entity's id
 * @param <DTO> DTO object to convert
 */
public abstract class AbstractFacade<E, ID, DTO> implements Facade<E, ID, DTO> {

	protected abstract Class<DTO> getDtoClass();

	protected abstract BaseService<E, ID> getService();

	public abstract ServiceResult<E> convertToEntity(DTO dto);

	public ServiceResult<Collection<E>> convertToEntity(Collection<DTO> dtos) {
		return ServiceResult.mapAll(dtos, this::convertToEntity);
	}

	protected DTO convertToDto(E e) {
		return ObjectMapper.map(e, getDtoClass());
	}

	protected Collection<DTO> convertToDto(Collection<E> collection) {
		return ObjectMapper.mapAll(collection, getDtoClass());
	}

	@Override
	public ServiceResult<DTO> getById(ID id) {
		return getService().getById(id).upgrade(this::convertToDto);
	}

	@Override
	public ServiceResult<Collection<DTO>> getAll() {
		return getService().getAll().upgrade(this::convertToDto);
	}
}
