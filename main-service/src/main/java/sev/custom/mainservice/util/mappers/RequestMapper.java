package sev.custom.mainservice.util.mappers;

import lombok.experimental.UtilityClass;
import sev.custom.mainservice.dto.RequestFullDto;
import sev.custom.mainservice.dto.RequestIncomeDto;
import sev.custom.mainservice.model.Request;
import sev.custom.mainservice.model.Resource;
import sev.custom.mainservice.model.User;

import static sev.custom.mainservice.util.mappers.ResourceMapper.toResourceFullDto;

@UtilityClass
public class RequestMapper {

    public static Request toRequest(RequestIncomeDto dto, User user, Resource resource) {
        Request request = new Request();

        request.setRequestType(dto.getRequestType());
        request.setUser(user);
        request.setResource(resource);
        request.setResourceLocationName(dto.getResourceLocationName());
        request.setResourceAmount(dto.getResourceAmount());

        return request;
    }

    public static RequestFullDto toRequestFullDto(Request request) {
        return RequestFullDto.builder()
                .requestId(request.getId())
                .name(request.getUser().getName())
                .requestType(request.getRequestType())
                .ticketStartDate(request.getUser().getTicketStartDate())
                .ticketSeries(request.getUser().getTicketSeries())
                .ticketNumber(request.getUser().getTicketNumber())
                .resource(toResourceFullDto(request.getResource()))
                .resourceLocationName(request.getResourceLocationName())
                .resourceAmount(request.getResourceAmount())
                .requestState(request.getRequestState())
                .build();
    }
}
