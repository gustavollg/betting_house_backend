package com.bettinghouse.api.architecture.validator;

import com.bettinghouse.api.architecture.model.AbstractEntity;

public abstract class CRUDValidator<T extends AbstractEntity> extends AbstractValidator {

    public CRUDValidator() {
    }

    public abstract void validateBeforeSave(T entity);

    public abstract void validateBeforeRemove(T entity);
}