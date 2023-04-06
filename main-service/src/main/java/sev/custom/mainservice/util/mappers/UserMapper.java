package sev.custom.mainservice.util.mappers;

import lombok.experimental.UtilityClass;
import sev.custom.mainservice.dto.RequestIncomeDto;
import sev.custom.mainservice.model.User;

@UtilityClass
public class UserMapper {

    public static User toUser(RequestIncomeDto dto) {
        User user = new User();

        user.setTicketSeries(dto.getTicketSeries());
        user.setTicketNumber(dto.getTicketNumber());
        user.setName(dto.getName());
        user.setTicketStartDate(dto.getTicketStartDate());

        return user;
    }
}
