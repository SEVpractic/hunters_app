package sev.custom.mainservice.dto;

import lombok.Builder;
import lombok.Getter;
import sev.custom.mainservice.model.States;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Getter
public class ResourceFullDto {
    private final Long id;
    private final String name;
    private final Integer quota;
    private LocalDateTime requestAcceptingBegin;
    private LocalDateTime requestAcceptingEnd;
    private final States resourceState;
}
