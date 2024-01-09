package com.bonjung.camong.experience.api.request;

public record StepCreateRequest(
        String title,
        String line,
        Long duration
) {
}
