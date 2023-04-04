package sev.custom.mainservice.storage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<RequestRepo, Long> {
}
