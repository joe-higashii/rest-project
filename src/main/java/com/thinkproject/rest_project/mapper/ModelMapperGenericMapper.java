//ModelMapperGenericMapper.java
package com.thinkproject.rest_project.mapper;

import org.modelmapper.ModelMapper;

public class ModelMapperGenericMapper<E, D> implements GenericMapper<E, D> {
    private final ModelMapper modelMapper;
    private final Class<D> dtoClass;
    private final Class<E> entityClass;

    public ModelMapperGenericMapper(ModelMapper modelMapper, Class<E> entityClass, Class<D> dtoClass) {
        this.modelMapper = modelMapper;
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public D toDto(E entity) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public E toEntity(D dto) {
        return modelMapper.map(dto, entityClass);
    }
}

