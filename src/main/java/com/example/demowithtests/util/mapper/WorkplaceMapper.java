package com.example.demowithtests.util.mapper;

import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkplaceMapper {
    Workplace workplaceRequestDtoToWorkplace(WorkplaceRequestDto workplaceRequestDto);
    WorkplaceResponseDto workplaceToResponseDto(Workplace workplace);
}
