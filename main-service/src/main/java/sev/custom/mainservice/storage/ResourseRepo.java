package sev.custom.mainservice.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import sev.custom.mainservice.model.Resource;

public interface ResourseRepo extends JpaRepository<Resource, Long> {
}
