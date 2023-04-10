package sev.custom.mainservice.service;

import sev.custom.mainservice.dto.RequestFullDto;
import sev.custom.mainservice.dto.RequestIncomeDto;

public interface RequestService {
    RequestFullDto create(RequestIncomeDto dto);

    RequestFullDto update(RequestIncomeDto dto, long requestId);

    RequestFullDto get(long requestId);

    void delete(long requestId);
}
