package sev.custom.mainservice.dto;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import sev.custom.mainservice.model.RequestType;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static sev.custom.mainservice.util.TestDataCreator.createRequestDto;
import static sev.custom.mainservice.util.TestDataCreator.createResourceDto;

@JsonTest
class DtoTest {
    @Autowired
    private JacksonTester<RequestIncomeDto> requestJson;
    @Autowired
    private JacksonTester<ResourceIncomeDto> resourceJson;

    @Test
    @SneakyThrows
    void RequestDtoTest() {
        RequestIncomeDto dto = createRequestDto();

        Optional<JsonContent<RequestIncomeDto>> result = Optional.of(requestJson.write(dto));

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(i -> {
                    assertThat(i)
                            .extractingJsonPathStringValue("$.name")
                            .isEqualTo("Rick Sanchez");
                    assertThat(i)
                            .extractingJsonPathStringValue("$.requestType")
                            .isEqualTo(RequestType.DRAW_TYPES.name());
                    assertThat(i)
                            .extractingJsonPathStringValue("$.ticketSeries")
                            .isEqualTo("100");
                    assertThat(i)
                            .extractingJsonPathStringValue("$.ticketNumber")
                            .isEqualTo("100000");
                    assertThat(i)
                            .extractingJsonPathNumberValue("$.resourceId")
                            .isEqualTo(1);
                    assertThat(i)
                            .extractingJsonPathStringValue("$.resourceLocationName")
                            .isEqualTo("Moon");
                    assertThat(i)
                            .extractingJsonPathNumberValue("$.resourceAmount")
                            .isEqualTo(10);
                });
    }

    @Test
    @SneakyThrows
    void ResourceDtoTest() {
        ResourceIncomeDto dto = createResourceDto();

        Optional<JsonContent<ResourceIncomeDto>> result = Optional.of(resourceJson.write(dto));

        assertThat(result)
                .isPresent()
                .hasValueSatisfying(i -> {
                    assertThat(i)
                            .extractingJsonPathStringValue("$.name")
                            .isEqualTo("Duck");
                    assertThat(i)
                            .extractingJsonPathNumberValue("$.quota")
                            .isEqualTo(10);
                });
    }
}
