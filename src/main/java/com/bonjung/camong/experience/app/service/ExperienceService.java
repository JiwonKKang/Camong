package com.bonjung.camong.experience.app.service;

import com.bonjung.camong.experience.app.dto.ExperienceDto;
import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    public void createExperience(ExperienceDto request) {

        Experience saved = experienceRepository.save(request.toEntity());
        log.info("ExperienceService - createExperience {}", saved);
    }
}
