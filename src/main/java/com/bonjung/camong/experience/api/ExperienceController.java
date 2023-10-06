package com.bonjung.camong.experience.api;

import com.bonjung.camong.experience.app.dto.ExperienceDto;
import com.bonjung.camong.experience.app.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/experiences")
    public String createExperience(@RequestBody ExperienceDto request) {
        experienceService.createExperience(request);
        return "success";
    }
}
