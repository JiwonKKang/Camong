package com.bonjung.camong.experience.app.service;

import com.bonjung.camong.common.exception.CustomException;
import com.bonjung.camong.common.exception.ErrorCode;
import com.bonjung.camong.experience.api.request.ExperienceCreateRequest;
import com.bonjung.camong.experience.api.response.ExperienceResponse;
import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final FileUploadService fileUploadService;

    public void createExperience(ExperienceCreateRequest request, MultipartFile image) {
        experienceRepository.save(
                Experience.of(
                        request,
                        fileUploadService.uploadFileInStorage(image)
                )
        );
    }

    public Page<ExperienceResponse> getExperiences(Pageable pageable) {
        return experienceRepository.findAll(pageable).map(ExperienceResponse::from);
    }

    public Experience getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXPERIENCE_NOT_FOUND));
    }
}
