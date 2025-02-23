//GenericMapper.java
package com.thinkproject.rest_project.mapper;

public interface GenericMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}

