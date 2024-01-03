package com.bonjung.camong.experience.app.service;

import com.bonjung.camong.common.exception.CustomException;
import com.bonjung.camong.common.exception.ErrorCode;
import com.bonjung.camong.experience.api.request.StepSequenceUpdateRequest;
import com.bonjung.camong.experience.api.request.StepCreateRequest;
import com.bonjung.camong.experience.api.request.StepUpdateRequest;
import com.bonjung.camong.experience.api.response.StepResponse;
import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.entity.MediaFile;
import com.bonjung.camong.experience.domain.entity.Step;
import com.bonjung.camong.experience.domain.repository.MediaFileRepository;
import com.bonjung.camong.experience.domain.repository.StepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class StepService {

    private final StepRepository stepRepository;
    private final FileUploadService fileUploadService;
    private final ExperienceService experienceService;
    private final MediaFileRepository mediaFileRepository;

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

    @Transactional
    public void updateSteps(List<StepSequenceUpdateRequest> requests) {
        for (StepSequenceUpdateRequest request : requests) {
            Step step = getStep(request.stepId());
            step.updateSequence(request.updatedSequence());
        }
    }

    public Step getStep(Long stepId) {
        return stepRepository.findById(stepId).orElseThrow(() ->
                new CustomException(ErrorCode.PAGE_NOT_FOUND));
    }

    public void deleteStep(Long stepId) {
        stepRepository.deleteById(stepId);
    }

    @Transactional
    public void updateStep(Long stepId, StepUpdateRequest request, MultipartFile image, MultipartFile voice) {
        Step step = getStep(stepId);

        MediaFile imageFile = (image != null) ? fileUploadService.uploadFileInStorage(image) : null;
        MediaFile voiceFile = (voice != null) ? fileUploadService.uploadFileInStorage(voice) : null;

        if (Objects.nonNull(imageFile)) { // null이 아닐 경우 기존 이미지 삭제
            mediaFileRepository.delete(step.getImage());
            step.updateImageFile(imageFile);
        }

        if (Objects.nonNull(voiceFile)) { // null이 아닐 경우 기존 음성 파일 삭제
            mediaFileRepository.delete(step.getVoice());
            step.updateVoiceFile(voiceFile);
        }

        step.update(request);
    }

}
