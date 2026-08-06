package com.book.book.mappers;

import com.book.book.dtos.BookDto;
import com.book.book.entities.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, uses = { AuthorMapper.class })
public interface BookMapper {
    BookDto toDto(BookEntity entity);

    @Mapping(target = "id", ignore = true)
    BookEntity toEntity(BookDto dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(BookDto dto, @MappingTarget BookEntity entity);
}
