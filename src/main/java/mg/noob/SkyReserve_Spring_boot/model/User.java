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
@Table(name = "_user_")
public class User {
    @Id
    @ColumnDefault("nextval('_user__user_id_seq')")
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "role", nullable = false, length = 250)
    private String role;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

}