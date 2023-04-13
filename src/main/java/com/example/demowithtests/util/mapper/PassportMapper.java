package com.example.demowithtests.util.mapper;

import com.example.demowithtests.domain.passport.Passport;
import com.example.demowithtests.dto.passport.PassportRequestDto;
import com.example.demowithtests.dto.passport.PassportResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassportMapper {

    Passport fromRequestDto(PassportRequestDto requestDto);

    PassportRequestDto toRequestDto(Passport passport);

    PassportResponseDto toResponseDto(Passport passport);

    Passport fromResponseDto(PassportResponseDto responseDto);

}
