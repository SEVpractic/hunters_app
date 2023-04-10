package sev.custom.mainservice.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev.custom.mainservice.model.Resource;

public interface ResourceRepo extends JpaRepository<Resource, Long> {
}
