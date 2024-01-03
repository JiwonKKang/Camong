package com.bonjung.camong.experience.api.response;

import com.bonjung.camong.experience.domain.entity.Step;

public record StepResponse(
        Long stepId,
        String title,
        String line,
        Integer sequence,
        String imageUrl,
        String voiceUrl,
        long duration
) {
    public static StepResponse from(Step entity) {
        return new StepResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getLine(),
                entity.getSequence(),
                entity.getImage().getFileUrl(),
                entity.getVoice().getFileUrl(),
                entity.getDuration()
        );
    }
}
