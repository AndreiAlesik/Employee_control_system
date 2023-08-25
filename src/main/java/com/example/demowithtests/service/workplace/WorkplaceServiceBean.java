package com.example.demowithtests.service.workplace;


import com.example.demowithtests.domain.office.Workplace;
import com.example.demowithtests.repository.WorkplaceRepository;
import com.example.demowithtests.util.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class WorkplaceServiceBean implements WorkplaceService {

    private final WorkplaceRepository workplaceRepository;
    @Override
    public Workplace create(Workplace workplace) {
        log.debug("Service ==> create() - start: workplace = {}", workplace);
        workplace.setIsActive(Boolean.TRUE);
        Workplace savedWorkplace = workplaceRepository.save(workplace);
        log.debug("Service ==> create() - end: workplace = {}", savedWorkplace);
        return savedWorkplace;
    }

    @Override
    public Workplace getById(Integer id) {
        log.debug("Service ==> getById() - start: id = {}", id);
        var workplace = workplaceRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        log.debug("Service ==> getById() - start: workplace = {}", workplace);
        return workplace;
    }
}
