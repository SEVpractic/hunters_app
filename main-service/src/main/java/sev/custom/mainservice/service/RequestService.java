package sev.custom.mainservice.service;

public interface RequestService {
    requestDto create(requestIncomeDto dto);

    requestDto update(requestIncomeDto dto, long requestId);

    requestDto get(long requestId);

    void delete(long requestId);
}
