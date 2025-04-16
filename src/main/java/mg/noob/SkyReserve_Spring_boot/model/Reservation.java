package mg.noob.SkyReserve_Spring_boot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_id_gen")
    @SequenceGenerator(name = "reservation_id_gen", sequenceName = "reservation_reservation_id_seq", allocationSize = 1)
    @Column(name = "reservation_id", nullable = false)
    private Integer id;

    @Column(name = "reservation_date", nullable = false)
    private Instant reservationDate;

    @ColumnDefault("1")
    @Column(name = "seats_number", nullable = false)
    private Integer seatsNumber;

    @Column(name = "seats_number_children", nullable = false)
    private  Integer seatsNumberChildren;

    @ColumnDefault("false")
    @Column(name = "has_promotion", nullable = false)
    private Boolean hasPromotion = false;

    @ManyToOne( optional = false)
    @JoinColumn(name = "reservation_status_id", nullable = false)
    private mg.noob.SkyReserve_Spring_boot.model.ReservationStatus reservationStatus;

    @ManyToOne( optional = false)
    @JoinColumn(name = "seat_type_id", nullable = false)
    private mg.noob.SkyReserve_Spring_boot.model.SeatType seatType;

    @ManyToOne( optional = false)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne( optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}