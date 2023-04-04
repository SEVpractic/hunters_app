package sev.custom.mainservice.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class ResourceFullDto {
    private final Long resourceId;
    private final String resourceName;
    private final String resourceLocationName;
    private final Integer resourceAmount;
}
