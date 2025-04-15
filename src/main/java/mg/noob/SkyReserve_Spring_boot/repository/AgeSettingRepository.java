package mg.noob.SkyReserve_Spring_boot.repository;



import mg.noob.SkyReserve_Spring_boot.model.AgeSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeSettingRepository extends JpaRepository<AgeSetting, Long> {

}