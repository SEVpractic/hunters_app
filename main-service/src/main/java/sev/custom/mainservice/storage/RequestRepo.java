package sev.custom.mainservice.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev.custom.mainservice.model.Request;

public interface RequestRepo extends JpaRepository<Request, Long> {
}
