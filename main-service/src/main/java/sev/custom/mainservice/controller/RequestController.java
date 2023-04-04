package sev.custom.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sev.custom.mainservice.service.RequestService;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class RequestController {
    private final RequestService requestServise;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public requestDto create(@Validated(CreateValidationGroup.class) requestIncomeDto dto) {
        return requestServise.create(dto);
    }

    @PatchMapping(path = "/{requestId}")
    public requestDto update(@Validated(UpdateValidationGroup.class) requestIncomeDto dto,
                             @PathVariable("requestId") @Positive long requestId) {
        return requestServise.update(requestId);
    }

    @GetMapping(path = "/{requestId}")
    public requestDto get(@PathVariable("requestId") @Positive long requestId) {
        return requestServise.get(requestId);
    }

    @DeleteMapping(path = "/{requestId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("requestId") @Positive long requestId) {
        requestServise.delete(requestId);
    }
}
