package sev.custom.mainservice.service;

import sev.custom.mainservice.dto.ResourceFullDto;
import sev.custom.mainservice.dto.ResourceIncomeDto;

public interface ResourceService {

    ResourceFullDto create(ResourceIncomeDto dto);

    ResourceFullDto update(ResourceIncomeDto dto, long resourceId);

    ResourceFullDto get(long resourceId);

    void delete(long resourceId);
}
