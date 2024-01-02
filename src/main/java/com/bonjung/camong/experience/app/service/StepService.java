package com.bonjung.camong.experience.app.service;

import com.bonjung.camong.experience.api.request.StepCreateRequest;
import com.bonjung.camong.experience.api.response.StepResponse;
import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.entity.MediaFile;
import com.bonjung.camong.experience.domain.entity.Step;
import com.bonjung.camong.experience.domain.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StepService {

    private final StepRepository stepRepository;
    private final FileUploadService fileUploadService;
    private final ExperienceService experienceService;

    public void createStep(Long experienceId,
                           StepCreateRequest request,
                           MultipartFile image,
                           MultipartFile voice) {

        MediaFile imageFile = fileUploadService.uploadFileInStorage(image);
        MediaFile voiceFile = fileUploadService.uploadFileInStorage(voice);
        Experience experience = experienceService.getExperienceById(experienceId);
        stepRepository.save(Step.of(request, experience, imageFile, voiceFile));
    }

    public List<StepResponse> getSteps(Long experienceId) {
        return stepRepository.findByExperience_Id(experienceId).stream()
                .sorted(Comparator.comparing(Step::getSequence))
                .map(StepResponse::from)
                .toList();
    }
}
