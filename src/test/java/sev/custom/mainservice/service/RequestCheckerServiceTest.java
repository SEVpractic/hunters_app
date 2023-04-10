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
import sev.custom.mainservice.model.States;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static sev.custom.mainservice.service.TestDataCreator.createRequestDto;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class RequestCheckerServiceTest {
    private final RequestCheckerService requestCheckerService;
    private final RequestService requestService;

    @Test
    @Order(0)
    @Sql(value = {"/test-schema.sql", "/test-data-resource.sql"})
    void checkTest() {
        requestCheckerService.startChecking();
        RequestIncomeDto dto = createRequestDto();

        Optional<RequestFullDto> fullDto = Optional.of(requestService.create(dto));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.APPROVED);
                });
    }

    @Test
    @Order(1)
    void checkTestFail_alreadyExist() {
        RequestIncomeDto dto = createRequestDto();

        Optional<RequestFullDto> fullDto = Optional.of(requestService.create(dto));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.CANCELED);
                });
    }

    @Test
    @Order(2)
    @Sql(value = {"/test-schema.sql", "/test-data-resource.sql"})
    void checkAllTest() {
        requestCheckerService.stopChecking();
        RequestIncomeDto dto = createRequestDto();

        Optional<RequestFullDto> fullDtoFirst = Optional.of(requestService.create(dto));
        Optional<RequestFullDto> fullDtoLast = Optional.of(requestService.create(dto));

        assertThat(fullDtoFirst)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.PENDING);
                });
        assertThat(fullDtoLast)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.PENDING);
                });

        requestCheckerService.checkAll();

        fullDtoFirst = Optional.of(requestService.get(1L));
        fullDtoLast = Optional.of(requestService.get(2L));

        assertThat(fullDtoFirst)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.APPROVED);
                });
        assertThat(fullDtoLast)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("requestState", States.CANCELED);
                });
    }
}
