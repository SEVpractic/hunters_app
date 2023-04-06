package sev.custom.mainservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.util.RequestType;
import sev.custom.mainservice.util.validation.CreateValidationGroup;
import sev.custom.mainservice.util.validation.UpdateValidationGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestIncomeDto {
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private final String name;
    @NotNull(groups = CreateValidationGroup.class)
    private final RequestType requestType;
    @NotNull(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private LocalDateTime ticketStartDate;
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private final String ticketSeries;
    @NotBlank(groups = {CreateValidationGroup.class, UpdateValidationGroup.class})
    private final String ticketNumber;
    @NotNull(groups = {CreateValidationGroup.class})
    @Positive(groups = {CreateValidationGroup.class})
    private final Long resourceId;
    @NotBlank(groups = CreateValidationGroup.class)
    private final String resourceLocationName;
    @NotNull(groups = {CreateValidationGroup.class})
    @Positive(groups = {CreateValidationGroup.class})
    private final Integer resourceAmount;
}