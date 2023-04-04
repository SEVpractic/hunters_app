package sev.custom.mainservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev.custom.mainservice.storage.RequestRepo;
import sev.custom.mainservice.storage.ResourseRepo;
import sev.custom.mainservice.storage.UserRepo;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RequestServiceImpl implements RequestService {
    private final RequestRepo requestRepo;

    private final UserRepo userRepo;

    private final ResourseRepo resourseRepo;

    @Override
    public requestDto create(requestIncomeDto dto) {
        return null;
    }

    @Override
    public requestDto update(requestIncomeDto dto, long requestId) {
        return null;
    }

    @Override
    public requestDto get(long requestId) {
        return null;
    }

    @Override
    public void delete(long requestId) {

    }
}