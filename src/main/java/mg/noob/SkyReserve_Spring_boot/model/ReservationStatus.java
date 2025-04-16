package mg.noob.SkyReserve_Spring_boot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "reservation_status")
public class ReservationStatus {
    @Id
    @ColumnDefault("nextval('reservation_status_reservation_status_id_seq')")
    @Column(name = "reservation_status_id", nullable = false)
    private Integer id;

    @Column(name = "reservation_name", nullable = false, length = 250)
    private String reseravtionName;

}