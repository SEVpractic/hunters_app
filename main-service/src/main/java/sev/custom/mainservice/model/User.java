package sev.custom.mainservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @EmbeddedId
    private UserPk userPk;
    @Column(name = "name")
    private String name;
    @Column(name = "ticket_start_date")
    private LocalDateTime ticketStartDate;

    @Embeddable
    @NoArgsConstructor
    @Getter
    @Setter
    public static class UserPk implements Serializable {
        @Column(name = "ticket_series")
        private Integer ticketSeries;
        @Column(name = "ticket_number")
        private Integer ticketNumber;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserPk userPk = (UserPk) o;
            return Objects.equals(ticketSeries, userPk.ticketSeries) && Objects.equals(ticketNumber, userPk.ticketNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ticketSeries, ticketNumber);
        }
    }
}
