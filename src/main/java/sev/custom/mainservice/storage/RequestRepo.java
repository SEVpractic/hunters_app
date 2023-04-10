package sev.custom.mainservice.storage;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sev.custom.mainservice.model.Request;
import sev.custom.mainservice.model.Resource;
import sev.custom.mainservice.model.User;

import java.util.List;
import java.util.Set;

public interface RequestRepo extends JpaRepository<Request, Long> {

    @Query("select r from Request as r where r.requestState like '0' ")
    List<Request> findAllByRequestState(String state, Sort sort);

    @Query("select r from Request as r " +
            "where r.requestState like '1' " +
            "and r.user in :users " +
            "and r.resource in :resources ")
    Set<Request> findAllApproved(@Param("users") Set<User> users,
                                 @Param("resources") Set<Resource> resources);
}
