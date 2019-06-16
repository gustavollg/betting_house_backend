package com.bettinghouse.api.model;

import com.bettinghouse.api.architecture.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "sport")
@SequenceGenerator(name = "default_generator", sequenceName = "sport_id_seq", allocationSize = 1)
public class Sport extends AbstractEntity {
    
    @NotBlank(message = "NAME_IS_NULL")
    private String name;

    @NotNull(message = "HAS_DRAW_IS_NULL")
    private boolean hasDraw;

    public Sport() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasDraw() {
        return hasDraw;
    }

    public void setHasDraw(boolean hasDraw) {
        this.hasDraw = hasDraw;
    }
}
