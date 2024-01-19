package com.bonjung.camong.experience.api.response;

import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.entity.ExperienceStatus;

public record ExperienceResponse(
        Long experienceId,
        String title,
        String imageUrl,
        boolean isEnabled
) {
    public static ExperienceResponse from(Experience experience) {
        return new ExperienceResponse(
                experience.getId(),
                experience.getTitle(),
                experience.getMainImage().getFileUrl(),
                experience.getExperienceStatus() == ExperienceStatus.ON
        );
    }

}
