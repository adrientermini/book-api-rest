package com.book.book.mappers;

import com.book.book.dtos.AuthorDto;
import com.book.book.entities.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface AuthorMapper {
    AuthorDto toDto(AuthorEntity entity);

    @Mapping(target = "id", ignore = true)
    AuthorEntity toEntity(AuthorDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(AuthorDto dto, @MappingTarget AuthorEntity entity);
}
