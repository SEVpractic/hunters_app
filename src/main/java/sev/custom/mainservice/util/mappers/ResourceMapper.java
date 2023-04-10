package sev.custom.mainservice.util.mappers;

import lombok.experimental.UtilityClass;
import sev.custom.mainservice.dto.ResourceFullDto;
import sev.custom.mainservice.dto.ResourceIncomeDto;
import sev.custom.mainservice.model.Resource;
import sev.custom.mainservice.model.States;

@UtilityClass
public class ResourceMapper {

    public static Resource toResource(ResourceIncomeDto dto) {
        Resource resource = new Resource();

        resource.setResourceName(dto.getName());
        resource.setQuota(dto.getQuota());
        resource.setRequestAcceptingBegin(dto.getRequestAcceptingBegin());
        resource.setRequestAcceptingEnd(dto.getRequestAcceptingEnd());

        return resource;
    }

    public static ResourceFullDto toResourceFullDto(Resource resource) {
        return ResourceFullDto.builder()
                .id(resource.getId())
                .name(resource.getResourceName())
                .quota(resource.getQuota())
                .requestAcceptingBegin(resource.getRequestAcceptingBegin())
                .requestAcceptingEnd(resource.getRequestAcceptingEnd())
                .resourceState(States.valueOf(resource.getResourceState()))
                .build();
    }
}
