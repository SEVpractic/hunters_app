package sev.custom.mainservice.service;

import sev.custom.mainservice.model.Request;

public interface RequestCheckerService {
    void startChecking();

    void stopChecking();

    void checkAll();

    void check(Request request);
}
