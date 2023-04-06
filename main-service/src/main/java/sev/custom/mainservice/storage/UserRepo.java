package sev.custom.mainservice.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev.custom.mainservice.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByTicketSeriesAndTicketNumber(String series, String number);
}
