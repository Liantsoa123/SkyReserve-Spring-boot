package mg.noob.SkyReserve_Spring_boot.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "age_setting")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgeSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "age_setting_id")
    private Long ageSettingId;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "discount_percentage", nullable = false)
    private Double discountPercentage;
}