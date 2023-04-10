package sev.custom.mainservice.util;

import lombok.experimental.UtilityClass;
import sev.custom.mainservice.dto.RequestIncomeDto;
import sev.custom.mainservice.dto.ResourceIncomeDto;
import sev.custom.mainservice.model.RequestType;

import java.time.LocalDateTime;

@UtilityClass
public class TestDataCreator {
    public static RequestIncomeDto createRequestDto() {
        return RequestIncomeDto.builder()
                .name("Rick Sanchez")
                .requestType(RequestType.DRAW_TYPES)
                .ticketStartDate(LocalDateTime.now().minusYears(40))
                .ticketSeries("100")
                .ticketNumber("100000")
                .resourceId(1L)
                .resourceLocationName("Moon")
                .resourceAmount(10)
                .build();
    }

    public static RequestIncomeDto updateRequestDto() {
        return RequestIncomeDto.builder()
                .name("Morty McFly")
                .resourceId(2L)
                .resourceLocationName("Earth")
                .build();
    }

    public static ResourceIncomeDto createResourceDto() {
        return ResourceIncomeDto.builder()
                .name("Duck")
                .quota(10)
                .requestAcceptingBegin(LocalDateTime.now().minusMonths(1))
                .requestAcceptingEnd(LocalDateTime.now().plusMonths(1))
                .build();
    }

    public static ResourceIncomeDto updateResourceDto() {
        return ResourceIncomeDto.builder()
                .name("Hog")
                .quota(1)
                .requestAcceptingBegin(LocalDateTime.now().minusMonths(2))
                .requestAcceptingEnd(LocalDateTime.now().plusMonths(2))
                .build();
    }
}
