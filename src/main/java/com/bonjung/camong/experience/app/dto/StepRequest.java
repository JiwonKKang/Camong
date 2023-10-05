package com.bonjung.camong.experience.app.dto;

import com.bonjung.camong.experience.domain.entity.Step;

public record StepRequest(
        Integer sequence,
        String line
) {
    public Step toEntity() {
        return Step.of(
                sequence,
                line
        );
    }
}
