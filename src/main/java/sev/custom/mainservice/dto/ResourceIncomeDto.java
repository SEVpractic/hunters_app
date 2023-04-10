package sev.custom.mainservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.util.validation.CreateValidationGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceIncomeDto {
    @NotBlank(groups = CreateValidationGroup.class)
    private final String name;
    @NotNull(groups = {CreateValidationGroup.class})
    @Positive(groups = {CreateValidationGroup.class})
    private final Integer quota;
    @NotNull(groups = {CreateValidationGroup.class})
    private LocalDateTime requestAcceptingBegin;
    @NotNull(groups = {CreateValidationGroup.class})
    private LocalDateTime requestAcceptingEnd;
}
