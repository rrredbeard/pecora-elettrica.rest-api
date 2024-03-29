package com.pecora_elettrica.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.stream.Collectors;

public class ObjectMapper {

	private static ModelMapper modelMapper;

	/**
	 * Model mapper property setting are specified in the following block.
	 * Default property matching strategy is set to Strict see {@link MatchingStrategies}
	 * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
	 */
	static {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Hide from public usage.
	 */
	private ObjectMapper() {
	}

	/**
	 * <p>Note: outClass object must have default constructor with no arguments</p>
	 *
	 * @param <DTO>      type of result object.
	 * @param <E>      type of source object to map from.
	 * @param entity   entity that needs to be mapped.
	 * @param outClass class of result object.
	 * @return new object of <code>outClass</code> type.
	 */
	public static <DTO, E> DTO map(final E entity, Class<DTO> outClass) {
		return modelMapper.map(entity, outClass);
	}

	/**
	 * <p>Note: outClass object must have default constructor with no arguments</p>
	 *
	 * @param entityList list of entities that needs to be mapped
	 * @param outCLass   class of result list element
	 * @param <D>        type of objects in result list
	 * @param <T>        type of entity in <code>entityList</code>
	 * @return list of mapped object with <code><D></code> type.
	 */
	public static <DTO, E> Collection<DTO> mapAll(final Collection<E> entityList, Class<DTO> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}

	/**
	 * Maps {@code source} to {@code destination}.
	 *
	 * @param source      object to map from
	 * @param destination object to map to
	 */
	public static <S, D> D map(final S source, D destination) {
		modelMapper.map(source, destination);
		return destination;
	}
}
