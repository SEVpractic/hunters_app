package sev.custom.mainservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev.custom.mainservice.model.Request;
import sev.custom.mainservice.model.Resource;
import sev.custom.mainservice.model.User;
import sev.custom.mainservice.storage.RequestRepo;
import sev.custom.mainservice.storage.ResourceRepo;
import sev.custom.mainservice.storage.UserRepo;
import sev.custom.mainservice.util.exceptions.EntityNotExistException;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UtilService {
    private final RequestRepo requestRepo;

    private final UserRepo userRepo;

    private final ResourceRepo resourceRepo;

    public Resource findResourceOrThrow(long resourceId) {
        return resourceRepo.findById(resourceId)
                .orElseThrow(() -> new EntityNotExistException(
                        String.format("Ресурс c id = %s не существует", resourceId))
                );
    }

    @Transactional
    public User findUserOrSave(User user) {
        return userRepo.findById(user.getUserPk())
                .orElseGet(() -> userRepo.save(user));
    }

    public Request findRequestOrThrow(long requestId) {
        return requestRepo.findById(requestId)
                .orElseThrow(() -> new EntityNotExistException(
                        String.format("Запрос c id = %s не существует", requestId))
                );
    }
}
