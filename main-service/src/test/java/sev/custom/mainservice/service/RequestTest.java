package sev.custom.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import sev.custom.mainservice.dto.RequestFullDto;
import sev.custom.mainservice.dto.RequestIncomeDto;
import sev.custom.mainservice.util.RequestType;
import sev.custom.mainservice.util.States;
import sev.custom.mainservice.util.exceptions.EntityNotExistException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class RequestTest {
    private final RequestService requestService;

    @Test
    @Order(0)
    @Sql(value = {"/test-schema.sql", "/test-data-resource.sql"})
    void createTest() {
        RequestIncomeDto dto = createDto();

        Optional<RequestFullDto> fullDto = Optional.of(requestService.create(dto));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestId", 1L);
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.PENDING);
                    assertThat(d.getResource()).hasFieldOrPropertyWithValue("id", 1L);
                });

    }

    @Test
    @Order(1)
    void getTest() {
        Optional<RequestFullDto> fullDto = Optional.of(requestService.get(1L));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestId", 1L);
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.PENDING);
                });
    }

    @Test
    @Order(2)
    void updateTest() {
        RequestIncomeDto dto = updateDto();

        Optional<RequestFullDto> fullDto = Optional.of(requestService.update(dto, 1L));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestId", 1L);
                    assertThat(d).hasFieldOrPropertyWithValue("name", "Rick Sanchez");
                    assertThat(d).hasFieldOrPropertyWithValue("resourceLocationName", "Earth");
                });
    }

    @Test
    @Order(3)
    void deleteTest() {
        requestService.delete(1L);

        assertThrows(EntityNotExistException.class, () -> requestService.get(1L));
    }

    private RequestIncomeDto createDto() {
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

    private RequestIncomeDto updateDto() {
        return RequestIncomeDto.builder()
                .name("Morty McFly")
                .resourceId(2L)
                .resourceLocationName("Earth")
                .build();
    }
}
