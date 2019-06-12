package com.bettinghouse.api.architecture.controller;

import com.bettinghouse.api.architecture.model.AbstractEntity;
import com.bettinghouse.api.architecture.service.CRUDService;
import com.bettinghouse.api.architecture.validator.CRUDValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class CRUDController<T extends AbstractEntity> {

    private CRUDValidator<T> validator;
    private CRUDService<T> service;

    public CRUDController() { }

    public CRUDController(CRUDValidator<T> validator, CRUDService<T> service) {
        this.validator = validator;
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        T entity = service.findById(id);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/all")
    public Iterable<T> findAll() {
        return service.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<T> save(@RequestBody @Valid T entity) {
        validator.validateBeforeSave(entity);
        service.executeBeforeSave(entity);
        T entityPersisted = service.save(entity);
        service.executeAfterSave(entity);
        return ResponseEntity.ok(entityPersisted);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(@PathVariable Long id) {
        T entity = service.findById(id);
        validator.validateBeforeRemove(entity);
        service.executeBeforeDelete(entity);
        service.delete(entity);
        service.executeAfterDelete(entity);
        return ResponseEntity.ok(entity);
    }
}