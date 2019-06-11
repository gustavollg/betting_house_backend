package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity(name = "sport")
@SequenceGenerator(name = "default_generator", sequenceName = "sport_id_seq", allocationSize = 1)
public class Sport extends AbstractEntity {
    
    @NotBlank(message = "NAME_IS_NULL")
    private String name;

    public Sport() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
