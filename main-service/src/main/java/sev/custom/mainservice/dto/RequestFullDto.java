package sev.custom.mainservice.dto;

import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.util.RequestType;
import sev.custom.mainservice.util.States;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
public class RequestFullDto {
    private final Long requestId;
    private final String name;
    private final RequestType requestType;
    private LocalDateTime ticketStartDate;
    private final Integer ticketSeries;
    private final Integer ticketNumber;
    private final ResourceFullDto resource;
    private final String resourceLocationName;
    private final Integer resourceAmount;
    private final States requestState;
}
