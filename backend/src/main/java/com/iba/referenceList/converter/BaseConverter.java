package com.iba.referenceList.converter;

import java.util.List;

public interface BaseConverter<Entity, Dto> {

    Dto toDto(Entity entity);

    List<Dto> toDto(List<Entity> entities);

    Entity toEntity(Dto dto);

    List<Entity> toEntity(List<Dto> dtos);

}
