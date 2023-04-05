package sev.custom.mainservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resources")
@Setter
@Getter
public class Resource {
    @Id
    @Column(name = "resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "resource_name")
    private String resourceName;
    @Column(name = "resource_quota")
    private Integer quota;
    @Column(name = "request_accepting_begin")
    private LocalDateTime requestAcceptingBegin;
    @Column(name = "request_accepting_end")
    private LocalDateTime requestAcceptingEnd;
    @Column(name = "resource_state")
    private String resourceState;
}
