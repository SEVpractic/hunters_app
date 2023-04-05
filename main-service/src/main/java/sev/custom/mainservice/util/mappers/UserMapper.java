package sev.custom.mainservice.util.mappers;

import lombok.experimental.UtilityClass;
import sev.custom.mainservice.dto.RequestIncomeDto;
import sev.custom.mainservice.model.User;
import sev.custom.mainservice.model.User.UserPk;

@UtilityClass
public class UserMapper {

    public static User toUser(RequestIncomeDto dto) {
        User user = new User();
        UserPk userPk = new UserPk();

        userPk.setTicketSeries(dto.getTicketSeries());
        userPk.setTicketNumber(dto.getTicketNumber());

        user.setUserPk(userPk);
        user.setName(dto.getName());
        user.setTicketStartDate(dto.getTicketStartDate());

        return user;
    }
}
