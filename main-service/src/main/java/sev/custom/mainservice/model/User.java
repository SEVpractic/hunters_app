package sev.custom.mainservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ticket_series")
    private String ticketSeries;
    @Column(name = "ticket_number")
    private String ticketNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "ticket_start_date")
    private LocalDateTime ticketStartDate;
}
