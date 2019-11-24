package com.pecora_elettrica.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import com.pecora_elettrica.api.dto.ErrorDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ServiceResult<V> {
	protected Object data;

	private ServiceResult(Object o) {
		this.data = Objects.requireNonNull(o);
	}

	public abstract boolean isSuccess();

	public abstract boolean isFailure();

	public abstract V getData();

	public abstract Exception getException();

	@SuppressWarnings("unchecked")
	private final <T> ServiceResult<T> propagateFailure() {
		if (isFailure())
			return (ServiceResult<T>) this;

		throw new IllegalStateException();
	}

	@SuppressWarnings("unchecked")
	public <T> ServiceResult<T> upgrade(Function<V, T> mapper) {
		Objects.requireNonNull(mapper);

		if (isFailure())
			return propagateFailure();

		return ServiceResult.requireNotNull(mapper.apply((V) data));
	}

	/** **/
	@SuppressWarnings("unchecked")
	public <T> ServiceResult<T> upgradeFromService(Function<V, ServiceResult<T>> serviceMapper) {
		Objects.requireNonNull(serviceMapper);

		if (isFailure())
			return propagateFailure();

		return serviceMapper.apply((V) data);
	}

	public ResponseEntity<?> toResponseEntity(HttpStatus success, HttpStatus failure) {

		if (isFailure()) {
			ErrorDTO e = ErrorDTO.builder().error(this.getException().getMessage()).status(failure).build();
			return ResponseEntity.status(failure).body(e);
		}
		return ResponseEntity.status(success).body(data);
	}

	public ResponseEntity<?> toResponseEntity(HttpStatus success) {
		return toResponseEntity(success, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> toResponseEntity() {
		return toResponseEntity(HttpStatus.OK);
	}

	/** public */

	public static <V> ServiceResult<V> submitSuccess(V value) {
		return new ServiceResultSuccess<V>(value);
	}

	@SuppressWarnings("unchecked")
	public static <V> ServiceResult<V> submitFailure(Exception ex) {
		return ((ServiceResult<V>) new ServiceResultFailure(ex));
	}

	/** utils */

	public static <V> ServiceResult<V> from(Optional<V> opt, Exception ex) {
		Objects.requireNonNull(opt);

		return opt.isPresent() ? submitSuccess(opt.get()) : submitFailure(ex);
	}

	public static <V> ServiceResult<V> from(Optional<V> opt) {
		return from(opt, new NoSuchElementException());
	}

	public static <V> ServiceResult<V> requireNotNull(V value) {
		return value == null ? submitFailure(new NullPointerException()) : submitSuccess(value);
	}

	public static <S, D> ServiceResult<Collection<D>> mapAll(Collection<S> src, Function<S, ServiceResult<D>> mapper) {

		Objects.requireNonNull(src);
		Objects.requireNonNull(mapper);

		if (src.isEmpty())
			return submitSuccess(Collections.emptyList());

		Collection<D> dest = new ArrayList<>(src.size());
		ServiceResult<D> result;

		for (S elem : src) {
			result = Objects.requireNonNull(mapper.apply(elem));
			if (result.isFailure())
				return result.propagateFailure();

			dest.add(result.getData());
		}

		return submitSuccess(dest);
	}

	/** impl */
	private static class ServiceResultSuccess<V> extends ServiceResult<V> {
		ServiceResultSuccess(V data) {
			super(data);
		}

		@Override
		public boolean isSuccess() {
			return true;
		}

		@Override
		public boolean isFailure() {
			return false;
		}

		@Override
		@SuppressWarnings("unchecked")
		public V getData() {
			return (V) data;
		}

		@Override
		public Exception getException() {
			return null;
		}
	}

	private static class ServiceResultFailure extends ServiceResult<Object> {

		ServiceResultFailure(Exception ex) {
			super(ex);
		}

		@Override
		public boolean isSuccess() {
			return false;
		}

		@Override
		public boolean isFailure() {
			return true;
		}

		@Override
		public Object getData() {
			return null;
		}

		@Override
		public Exception getException() {
			return (Exception) data;
		}
	}
}
