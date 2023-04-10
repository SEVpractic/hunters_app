package sev.custom.mainservice.dto;

import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.model.RequestType;
import sev.custom.mainservice.model.States;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
public class RequestFullDto {
    private final Long requestId;
    private final String name;
    private final RequestType requestType;
    private LocalDateTime ticketStartDate;
    private final String ticketSeries;
    private final String ticketNumber;
    private final ResourceFullDto resource;
    private final String resourceLocationName;
    private final Integer resourceAmount;
    private final States requestState;
}
