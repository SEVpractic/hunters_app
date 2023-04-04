package sev.custom.mainservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.util.RequestType;
import sev.custom.mainservice.util.validation.CreateValidationGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder(toBuilder = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestIncomeDto {
    @NotBlank(groups = CreateValidationGroup.class)
    private final String name;
    @NotNull(groups = CreateValidationGroup.class)
    private final RequestType requestType;
    @NotNull(groups = {CreateValidationGroup.class})
    @Positive(groups = {CreateValidationGroup.class})
    private final Integer ticketSeries;
    @NotNull(groups = {CreateValidationGroup.class})
    @Positive(groups = {CreateValidationGroup.class})
    private final Integer ticketNumber;
    @NotNull(groups = {CreateValidationGroup.class})
    @JsonProperty("requestedResources")
    private final ResourcesIncomeDto resourcesIncomeDto;
}