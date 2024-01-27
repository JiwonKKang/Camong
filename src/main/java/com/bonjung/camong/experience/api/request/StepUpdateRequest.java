package com.bonjung.camong.experience.api.request;

public record StepUpdateRequest(
        String title,
        String videoUrl,
        String line,
        Long duration
) {
}
