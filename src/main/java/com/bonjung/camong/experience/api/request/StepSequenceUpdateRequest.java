package com.bonjung.camong.experience.api.request;

public record StepSequenceUpdateRequest(
        Long stepId,
        Integer updatedSequence
) {
}
