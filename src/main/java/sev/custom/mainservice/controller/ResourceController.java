package sev.custom.mainservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sev.custom.mainservice.dto.ResourceFullDto;
import sev.custom.mainservice.dto.ResourceIncomeDto;
import sev.custom.mainservice.util.validation.CreateValidationGroup;
import sev.custom.mainservice.util.validation.UpdateValidationGroup;
import sev.custom.mainservice.service.ResourceService;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/resource")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResourceFullDto create(@Validated(CreateValidationGroup.class) @RequestBody ResourceIncomeDto dto) {
        return resourceService.create(dto);
    }

    @PatchMapping("/{resourceId}")
    public ResourceFullDto update(@Validated(UpdateValidationGroup.class) @RequestBody ResourceIncomeDto dto,
                                 @PathVariable("resourceId") @Positive long resourceId) {
        return resourceService.update(dto, resourceId);
    }

    @GetMapping("/{resourceId}")
    public ResourceFullDto get(@PathVariable("resourceId") @Positive long resourceId) {
        return resourceService.get(resourceId);
    }

    @DeleteMapping("/{resourceId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("resourceId") @Positive long resourceId) {
        resourceService.delete(resourceId);
    }
}
