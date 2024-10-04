package com.mg.station.station_perso.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class AbstractPrefixedIdEntity {

    @PrePersist
    protected abstract void beforePersist();
}
