package com.bonjung.camong.experience.api.request;

import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.entity.MediaFile;
import com.bonjung.camong.experience.domain.entity.Step;

public record StepCreateRequest(
        String title,
        String line,
        Boolean isImage,
        String videoUrl,
        Long duration
) {
    public Step toVideoStep() {
        return new Step(
                title,
                isImage,
                videoUrl
        );
    }

    public Step toImageStep(Experience experience, MediaFile imageFile, MediaFile voiceFile) {
        return new Step(
                experience.countSteps() + 1,
                title,
                line,
                isImage,
                experience,
                imageFile,
                voiceFile,
                duration
        );
    }
}
