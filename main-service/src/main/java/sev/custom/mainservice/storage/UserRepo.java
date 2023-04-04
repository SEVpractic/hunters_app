package sev.custom.mainservice.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev.custom.mainservice.model.User;

public interface UserRepo extends JpaRepository<User, User.UserPk> {
}
