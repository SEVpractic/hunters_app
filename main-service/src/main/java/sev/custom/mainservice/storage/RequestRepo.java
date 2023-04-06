package sev.custom.mainservice.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev.custom.mainservice.model.Request;

import java.util.Optional;

public interface RequestRepo extends JpaRepository<Request, Long> {
}
