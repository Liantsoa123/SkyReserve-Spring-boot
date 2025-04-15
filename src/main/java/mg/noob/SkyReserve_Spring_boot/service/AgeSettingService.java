package mg.noob.SkyReserve_Spring_boot.service;


import mg.noob.SkyReserve_Spring_boot.model.AgeSetting;
import mg.noob.SkyReserve_Spring_boot.repository.AgeSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgeSettingService {

    @Autowired
    private AgeSettingRepository ageSettingRepository;

    public List<AgeSetting> getAllAgeSettings() {
        return ageSettingRepository.findAll();
    }

    public Optional<AgeSetting> getAgeSettingById(Long id) {
        return ageSettingRepository.findById(id);
    }

    public AgeSetting saveAgeSetting(AgeSetting ageSetting) {
        return ageSettingRepository.save(ageSetting);
    }

    public void deleteAgeSetting(Long id) {
        ageSettingRepository.deleteById(id);
    }

    public AgeSetting updateAgeSetting(Long id, AgeSetting ageSettingDetails) {
        AgeSetting ageSetting = ageSettingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("AgeSetting not found for this id :: " + id));
        ageSetting.setAge(ageSettingDetails.getAge());
        ageSetting.setDiscountPercentage(ageSettingDetails.getDiscountPercentage());
        return ageSettingRepository.save(ageSetting);
    }
}