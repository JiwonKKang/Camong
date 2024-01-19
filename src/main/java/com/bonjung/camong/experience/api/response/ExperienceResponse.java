package com.bonjung.camong.experience.api.response;

import com.bonjung.camong.experience.domain.entity.Experience;

public record ExperienceResponse(
        Long experienceId,
        String title,
        String imageUrl,
        String experienceStatus
) {
    public static ExperienceResponse from(Experience experience) {
        return new ExperienceResponse(
                experience.getId(),
                experience.getTitle(),
                experience.getMainImage().getFileUrl(),
                experience.getExperienceStatus().name()
        );
    }
}
