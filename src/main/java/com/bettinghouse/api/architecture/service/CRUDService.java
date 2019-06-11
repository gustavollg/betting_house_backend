package com.bettinghouse.api.architecture.service;

import com.bettinghouse.api.architecture.exception.ApiErrorException;
import com.bettinghouse.api.architecture.model.AbstractEntity;
import com.bettinghouse.api.architecture.validator.dto.ApiErrorCode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class CRUDService<T extends AbstractEntity> {

    private CrudRepository<T, Long> repository;

    public CRUDService(CrudRepository<T, Long> repository) {
        this.repository = repository;
    }

    public T findById(Long id) {
        Optional<T> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new ApiErrorException(ApiErrorCode.NOT_FOUND);
        } else {
            return optional.get();
        }
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public abstract void executeBeforeSave(T entity);

    public abstract void executeAfterSave(T entity);

    public abstract void executeBeforeDelete(T entity);

    public abstract void executeAfterDelete(T entity);
}