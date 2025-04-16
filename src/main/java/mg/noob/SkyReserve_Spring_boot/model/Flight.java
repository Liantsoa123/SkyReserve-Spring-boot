package mg.noob.SkyReserve_Spring_boot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @ColumnDefault("nextval('flight_flight_id_seq')")
    @Column(name = "flight_id", nullable = false)
    private Integer id;

    @Column(name = "departure_date", nullable = false)
    private Instant departureDate;

    @Column(name = "arrival_date", nullable = false)
    private Instant arrivalDate;

}