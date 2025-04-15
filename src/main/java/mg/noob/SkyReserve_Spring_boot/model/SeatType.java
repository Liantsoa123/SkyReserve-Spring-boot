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
@Table(name = "seat_type")
public class SeatType {
    @Id
    @ColumnDefault("nextval('seat_type_seat_type_id_seq')")
    @Column(name = "seat_type_id", nullable = false)
    private Integer id;

    @Column(name = "type_name", nullable = false, length = 250)
    private String typeName;

}