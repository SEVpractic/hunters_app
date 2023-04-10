package sev.custom.mainservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sev.custom.mainservice.model.Request;
import sev.custom.mainservice.model.Resource;
import sev.custom.mainservice.model.States;
import sev.custom.mainservice.model.User;
import sev.custom.mainservice.storage.RequestRepo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RequestCheckerServiceImpl implements RequestCheckerService{
    private boolean checkOnFly = false;
    private final RequestRepo requestRepo;

    @Override
    @Transactional
    public void checkAll() {
        List<Request> requests = findPending();
        Map<User, List<Request>> approvedRequests = findApproved(requests);

        checkAll(requests, approvedRequests);
        log.info("Выполнена проверка всех ожидающих запросов");
    }

    @Override
    public void startChecking() {
        checkOnFly = true;
        log.info("Запущен режим проверки на лету");
    }

    @Override
    public void stopChecking() {
        checkOnFly = false;
        log.info("Остановлен режим проверки на лету");
    }

    @Override
    public void check(Request request) {
        if (!checkOnFly) return;

        List<Request> requests = List.of(request);
        Map<User, List<Request>> approvedRequests = findApproved(requests);

        checkAll(requests, approvedRequests);
        log.info("Выполнена проверка запроса");
    }

    private List<Request> findPending() {
        return requestRepo.findAllByRequestState(
                String.valueOf(States.PENDING.hashCode()),
                Sort.by(Sort.Direction.ASC, "created")
        );
    }

    private Map<User, List<Request>> findApproved(List<Request> requests) {
        Set<User> users = requests.stream()
                .map(Request::getUser)
                .collect(Collectors.toSet());
        Set<Resource> resources = requests.stream()
                .map(Request::getResource)
                .collect(Collectors.toSet());

        Set<Request> approvedRequests = requestRepo.findAllApproved(users, resources);

        return approvedRequests.stream()
                .collect(Collectors.toMap(
                        Request::getUser,
                        r -> approvedRequests.stream()
                                .filter(inner -> Objects.equals(inner.getUser().getId(), r.getUser().getId()))
                                .collect(Collectors.toList())
                ));
    }

    private void checkAll(List<Request> requests, Map<User, List<Request>> approvedRequests) {
        requests.forEach(request -> {
            if (isValid(request, approvedRequests)) {
                request.setRequestState(States.APPROVED);
                approvedRequests.merge(request.getUser(), List.of(request),
                        (a, b) -> Stream.concat(a.stream(), b.stream()).collect(Collectors.toList()));
            } else request.setRequestState(States.CANCELED);
        });
    }

    private boolean isValid(Request request, Map<User, List<Request>> approvedRequests) {
        return request.getResource().getQuota() >= request.getResourceAmount()
                && request.getResource().getRequestAcceptingBegin().isBefore(request.getCreated())
                && request.getResource().getRequestAcceptingEnd().isAfter(request.getCreated())
                && !(approvedRequests.containsKey(request.getUser()));
    }
}
