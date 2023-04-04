package sev.custom.mainservice.model;

import lombok.Getter;
import lombok.Setter;
import sev.custom.mainservice.util.RequestStates;
import sev.custom.mainservice.util.RequestType;

import javax.persistence.*;

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
    @JoinColumn(name = "user_ticket_series")
    @JoinColumn(name = "user_ticket_number")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private Resource resource;
    @Column(name = "resource_location_name")
    private String resourceLocationName;
    @Column(name = "resource_amount")
    private Integer resourceAmount;
    @Column(name = "request_states")
    private RequestStates requestState;
}
