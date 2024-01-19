package com.bonjung.camong.experience.app.service;

import com.bonjung.camong.common.exception.CustomException;
import com.bonjung.camong.common.exception.ErrorCode;
import com.bonjung.camong.experience.api.request.ExperienceCreateRequest;
import com.bonjung.camong.experience.api.response.ExperienceResponse;
import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.entity.ExperienceStatus;
import com.bonjung.camong.experience.domain.entity.MediaFile;
import com.bonjung.camong.experience.domain.repository.ExperienceRepository;
import com.bonjung.camong.experience.domain.repository.MediaFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final FileUploadService fileUploadService;
    private final MediaFileRepository mediaFileRepository;

    public void createExperience(ExperienceCreateRequest request, MultipartFile image) {
        experienceRepository.save(
                Experience.of(
                        request,
                        fileUploadService.uploadFileInStorage(image)
                )
        );
    }

    @Transactional
    public void updateExperience(Long experienceId, ExperienceCreateRequest request, MultipartFile image) {
        Experience experience = getExperienceById(experienceId);

        MediaFile imageFile = (image != null) ? fileUploadService.uploadFileInStorage(image) : null;

        if (Objects.nonNull(imageFile)) {
            mediaFileRepository.delete(experience.getMainImage()); // 기존 이미지 삭제
            experience.updateImage(imageFile);
        }

        experience.update(request);
    }

    @Transactional
    public void flipExperienceStatus(Long experienceId) {
        Experience experience = getExperienceById(experienceId);

        experience.flipExperienceStatus();
    }

    public Page<ExperienceResponse> getExperiences(Pageable pageable) {
        return experienceRepository.findAll(pageable).map(ExperienceResponse::from);
    }

    public Experience getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXPERIENCE_NOT_FOUND));
    }

    public void deleteExperience(Long experienceId) {
        experienceRepository.deleteById(experienceId);
    }

}
