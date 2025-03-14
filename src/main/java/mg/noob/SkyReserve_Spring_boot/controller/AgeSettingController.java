package mg.noob.SkyReserve_Spring_boot.controller;

import mg.noob.SkyReserve_Spring_boot.model.AgeSetting;
import mg.noob.SkyReserve_Spring_boot.service.AgeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/age-settings")
public class AgeSettingController {

    @Autowired
    private AgeSettingService ageSettingService;

    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) Long id , Model model) {
        AgeSetting ageSetting = id != null
                ? ageSettingService.getAgeSettingById(id).orElse(new AgeSetting())
                : new AgeSetting();
        model.addAttribute("ageSetting", ageSetting);
        return "age-settings/form";
    }

    @PostMapping("/save")
    public String saveAgeSetting(@ModelAttribute AgeSetting ageSetting) {
        if (ageSetting.getAgeSettingId() != null) {
            ageSettingService.updateAgeSetting(ageSetting.getAgeSettingId(), ageSetting);
        } else {
            ageSettingService.saveAgeSetting(ageSetting);
        }
        return "redirect:/age-settings/form";
    }
}