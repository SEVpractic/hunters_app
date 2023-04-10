package sev.custom.mainservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Setter
@Getter
public class Request {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "request_type")
    private RequestType requestType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private Resource resource;
    @Column(name = "resource_location_name")
    private String resourceLocationName;
    @Column(name = "resource_amount")
    private Integer resourceAmount;
    @Column(name = "request_state")
    private States requestState;
    @Column(name = "created")
    private LocalDateTime created;
}
