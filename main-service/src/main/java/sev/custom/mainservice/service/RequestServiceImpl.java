package sev.custom.mainservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev.custom.mainservice.dto.RequestFullDto;
import sev.custom.mainservice.dto.RequestIncomeDto;
import sev.custom.mainservice.model.Request;
import sev.custom.mainservice.model.Resource;
import sev.custom.mainservice.model.User;
import sev.custom.mainservice.storage.RequestRepo;
import sev.custom.mainservice.util.States;

import static sev.custom.mainservice.util.mappers.RequestMapper.toRequest;
import static sev.custom.mainservice.util.mappers.RequestMapper.toRequestFullDto;
import static sev.custom.mainservice.util.mappers.UserMapper.toUser;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RequestServiceImpl implements RequestService {
    private final UtilService utilService;

    private final RequestRepo requestRepo;

    @Override
    @Transactional
    public RequestFullDto create(RequestIncomeDto dto) {
        User user = utilService.findUserOrSave(toUser(dto));
        Resource resource = utilService.findResourceOrThrow(dto.getResourceId());
        Request request = toRequest(dto, user, resource);

        request.setRequestState(States.PENDING);
        request = requestRepo.save(request);
        log.info("Сохранен запрос id = {} ", request.getId());

        return toRequestFullDto(request);
    }

    @Override
    @Transactional
    public RequestFullDto update(RequestIncomeDto dto, long requestId) {
        Request request = utilService.findRequestOrThrow(requestId);

        update(dto, request);
        log.info("Обновлен запрос id = {} ", request.getId());

        return toRequestFullDto(request);
    }

    @Override
    public RequestFullDto get(long requestId) {
        Request request = utilService.findRequestOrThrow(requestId);
        log.info("Найден запрос id = {} ", request.getId());

        return toRequestFullDto(request);
    }

    @Override
    @Transactional
    public void delete(long requestId) {
        Request request = utilService.findRequestOrThrow(requestId);

        requestRepo.delete(request);
        log.info("Удален запрос id = {} ", requestId);
    }

    private void update(RequestIncomeDto dto, Request request) {
        if (dto.getRequestType() != null) request.setRequestType(dto.getRequestType());
        if (dto.getResourceId() != null) {
            Resource resource = utilService.findResourceOrThrow(dto.getResourceId());
            request.setResource(resource);
        }
        if (dto.getResourceLocationName() != null && !dto.getResourceLocationName().isBlank())
            request.setResourceLocationName(dto.getResourceLocationName());
        if (dto.getResourceAmount() != null) request.setResourceAmount(dto.getResourceAmount());
    }
}
