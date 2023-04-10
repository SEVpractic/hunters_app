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
import sev.custom.mainservice.dto.ResourceFullDto;
import sev.custom.mainservice.dto.ResourceIncomeDto;
import sev.custom.mainservice.util.exceptions.EntityNotExistException;
import sev.custom.mainservice.model.States;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sev.custom.mainservice.util.TestDataCreator.createResourceDto;
import static sev.custom.mainservice.util.TestDataCreator.updateResourceDto;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ResourceServiceTest {
    private final ResourceService resourceService;

    @Test
    @Order(0)
    @Sql(value = {"/test-schema.sql"})
    void createTest() {
        ResourceIncomeDto dto = createResourceDto();

        Optional<ResourceFullDto> fullDto = Optional.of(resourceService.create(dto));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("id", 1L);
                    assertThat(d).hasFieldOrPropertyWithValue("resourceState", States.APPROVED);
                });
    }

    @Test
    @Order(1)
    void getTest() {
        Optional<ResourceFullDto> fullDto = Optional.of(resourceService.get(1L));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("id", 1L);
                    assertThat(d).hasFieldOrPropertyWithValue("resourceState", States.APPROVED);
                });
    }

    @Test
    @Order(2)
    void updateTest() {
        ResourceIncomeDto dto = updateResourceDto();

        Optional<ResourceFullDto> fullDto = Optional.of(resourceService.update(dto, 1L));

        assertThat(fullDto)
                .isPresent()
                .hasValueSatisfying(d -> {
                    assertThat(d).hasFieldOrPropertyWithValue("id", 1L);
                    assertThat(d).hasFieldOrPropertyWithValue("resourceState", States.APPROVED);
                    assertThat(d).hasFieldOrPropertyWithValue("name", "Hog");
                    assertThat(d).hasFieldOrProperty("requestAcceptingBegin");
                    assertThat(d).hasFieldOrProperty("requestAcceptingEnd");
                    assertThat(d.getRequestAcceptingBegin()).isBefore(LocalDateTime.now().minusMonths(1));
                    assertThat(d.getRequestAcceptingEnd()).isAfter(LocalDateTime.now().plusMonths(1));
                });
    }

    @Test
    @Order(3)
    void deleteTest() {
        resourceService.delete(1L);

        assertThrows(EntityNotExistException.class, () -> resourceService.get(1L));
    }
}
