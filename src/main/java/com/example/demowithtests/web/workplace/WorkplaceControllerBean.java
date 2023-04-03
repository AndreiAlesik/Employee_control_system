package com.example.demowithtests.web.workplace;

import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.dto.workplace.WorkplaceRequestDto;
import com.example.demowithtests.dto.workplace.WorkplaceResponseDto;
import com.example.demowithtests.service.workplace.WorkplaceService;
import com.example.demowithtests.util.mapper.WorkplaceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Workplace", description = "Workplace API")
public class WorkplaceControllerBean implements WorkplaceController {

    private final WorkplaceService workplaceService;
    private final WorkplaceMapper workplaceMapper;
    @Override
    public WorkplaceResponseDto saveWorkplace(WorkplaceRequestDto workplaceRequestDto) {
        Workplace workplace=workplaceService.create(workplaceMapper.workplaceRequestDtoToWorkplace(workplaceRequestDto));
        return workplaceMapper.workplaceToResponseDto(workplace);
    }

    @Override
    public WorkplaceResponseDto getWorkplaceById(Integer id) {
        WorkplaceResponseDto workplaceResponseDto=workplaceMapper.workplaceToResponseDto(workplaceService.getById(id));
        return workplaceResponseDto;
    }
}
