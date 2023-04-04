package sev.custom.mainservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.util.RequestStates;
import sev.custom.mainservice.util.RequestType;

@Builder(toBuilder = true)
@Getter
public class RequestFullDto {
    private final Long requestId;
    private final String name;
    private final RequestType requestType;
    private final Integer ticketSeries;
    private final Integer ticketNumber;
    @JsonProperty("requestedResources")
    private final ResourcesIncomeDto resourcesIncomeDto;
    private final RequestStates states;
}
