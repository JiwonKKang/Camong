package com.bonjung.camong.experience.api.response;

import com.bonjung.camong.experience.domain.entity.Step;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
public record StepResponse(
        Long stepId,
        String title,
        String line,
        Integer sequence,
        Boolean isImage,
        String imageUrl,
        String videoUrl,
        String voiceUrl,
        Long duration
) {
    public static StepResponse from(Step entity) {

        Boolean isImage = entity.getIsImage();

        return new StepResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getLine(),
                entity.getSequence(),
                entity.getIsImage(),
                (isImage) ? entity.getImage().getFileUrl() : null,
                (isImage) ? null : entity.getVideoUrl(),
                (isImage) ? entity.getVoice().getFileUrl() : null,
                entity.getDuration()
        );
    }
}
