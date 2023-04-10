package sev.custom.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sev.custom.mainservice.service.RequestCheckerService;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class RequestCheckerController {
    private final RequestCheckerService checkerService;

    @PatchMapping("/startChecking")
    public void startChecking() {
        checkerService.startChecking();
    }

    @PatchMapping("/stopChecking")
    public void stopChecking() {
        checkerService.stopChecking();
    }

    @PatchMapping("/checkAll")
    public void checkAll() {
        checkerService.checkAll();
    }
}
