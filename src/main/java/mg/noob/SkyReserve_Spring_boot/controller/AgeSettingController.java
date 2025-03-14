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
    public String showForm( Model model) {
        AgeSetting ageSetting = ageSettingService.getAgeSettingById(1L).orElse(new AgeSetting());

        model.addAttribute("ageSetting", ageSetting);
        return "age-settings/form";
    }

    @PostMapping("/save")
    public String saveAgeSetting(@ModelAttribute AgeSetting ageSetting, Model model)  {
        String message = "";
        if (ageSetting.getAgeSettingId() != null) {
            ageSettingService.updateAgeSetting(ageSetting.getAgeSettingId(), ageSetting);
            message = "Age setting updated successfully";
        } else {
            ageSettingService.saveAgeSetting(ageSetting);
            message = "Age setting saved successfully";
        }
        model.addAttribute("message", message);
        model.addAttribute("ageSetting", ageSetting);
        return "age-settings/form";

    }
}