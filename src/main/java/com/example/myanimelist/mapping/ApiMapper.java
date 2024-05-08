package com.example.myanimelist.mapping;

import com.example.myanimelist.api.dto.TitleDto;
import com.example.myanimelist.api.entities.Title;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ApiMapper {

    //ApiMapper INSTANCE = Mappers.getMapper(ApiMapper.class);

    //TitleDto toTitleDto(Title title);
}
