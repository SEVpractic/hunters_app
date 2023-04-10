package sev.custom.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sev.custom.mainservice.dto.RequestFullDto;
import sev.custom.mainservice.dto.RequestIncomeDto;
import sev.custom.mainservice.util.validation.CreateValidationGroup;
import sev.custom.mainservice.util.validation.UpdateValidationGroup;
import sev.custom.mainservice.service.RequestService;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class RequestController {
    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public RequestFullDto create(@Validated(CreateValidationGroup.class) @RequestBody RequestIncomeDto dto) {
        return requestService.create(dto);
    }

    @PatchMapping("/{requestId}")
    public RequestFullDto update(@Validated(UpdateValidationGroup.class) @RequestBody RequestIncomeDto dto,
                                 @PathVariable("requestId") @Positive long requestId) {
        return requestService.update(dto, requestId);
    }

    @GetMapping("/{requestId}")
    public RequestFullDto get(@PathVariable("requestId") @Positive long requestId) {
        return requestService.get(requestId);
    }

    @DeleteMapping("/{requestId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("requestId") @Positive long requestId) {
        requestService.delete(requestId);
    }
}
