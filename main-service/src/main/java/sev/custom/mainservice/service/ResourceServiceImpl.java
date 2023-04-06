package sev.custom.mainservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev.custom.mainservice.dto.ResourceFullDto;
import sev.custom.mainservice.dto.ResourceIncomeDto;
import sev.custom.mainservice.model.Resource;
import sev.custom.mainservice.storage.ResourceRepo;
import sev.custom.mainservice.util.States;

import static sev.custom.mainservice.util.mappers.ResourceMapper.toResource;
import static sev.custom.mainservice.util.mappers.ResourceMapper.toResourceFullDto;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ResourceServiceImpl implements ResourceService {
    private final UtilService utilService;
    private final ResourceRepo resourceRepo;

    @Override
    @Transactional
    public ResourceFullDto create(ResourceIncomeDto dto) {
        Resource resource = toResource(dto);

        resource.setResourceState(States.APPROVED.name());
        resource = resourceRepo.save(resource);
        log.info("Сохранен ресурс id = {} ", resource.getId());

        return toResourceFullDto(resource);
    }

    @Override
    @Transactional
    public ResourceFullDto update(ResourceIncomeDto dto, long resourceId) {
        Resource resource = utilService.findResourceOrThrow(resourceId);

        update(dto, resource);
        log.info("Обновлен ресурс id = {} ", resource.getId());

        return toResourceFullDto(resource);
    }

    @Override
    public ResourceFullDto get(long resourceId) {
        Resource resource = utilService.findResourceOrThrow(resourceId);
        log.info("Найден запрос id = {} ", resource.getId());

        return toResourceFullDto(resource);
    }

    @Override
    @Transactional
    public void delete(long resourceId) {
        Resource resource = utilService.findResourceOrThrow(resourceId);

        resourceRepo.delete(resource);
        log.info("Удален ресурс id = {} ", resourceId);
    }

    private void update(ResourceIncomeDto dto, Resource resource) {
        if (dto.getName() != null && !dto.getName().isBlank()) resource.setResourceName(dto.getName());
        if (dto.getQuota() != null) resource.setQuota(dto.getQuota());
        if (dto.getRequestAcceptingBegin() != null) resource.setRequestAcceptingBegin(dto.getRequestAcceptingBegin());
        if (dto.getRequestAcceptingEnd() != null) resource.setRequestAcceptingEnd(dto.getRequestAcceptingEnd());
    }
}
