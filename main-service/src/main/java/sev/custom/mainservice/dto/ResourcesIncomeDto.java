package sev.custom.mainservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.util.validation.CreateValidationGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder(toBuilder = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourcesIncomeDto {
    @NotBlank(groups = CreateValidationGroup.class)
    private final String resourceName;
    @NotBlank(groups = CreateValidationGroup.class)
    private final String resourceLocationName;
    @NotNull(groups = {CreateValidationGroup.class})
    @Positive(groups = {CreateValidationGroup.class})
    private final Integer resourceAmount;
}
