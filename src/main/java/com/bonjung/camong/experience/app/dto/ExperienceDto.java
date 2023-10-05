package com.bonjung.camong.experience.app.dto;


import com.bonjung.camong.experience.domain.entity.Experience;
import com.bonjung.camong.experience.domain.entity.Material;

import java.util.List;
import java.util.Map;

public record ExperienceDto (
        String title,
        String description,
        Map<String, String> ingredients,
        List<StepRequest> steps
) {
    public Experience toEntity() {
        return Experience.of(
                title,
                description,
                ingredients.entrySet().stream()
                        .map(entry -> Material.of(entry.getKey(), entry.getValue()))
                        .toList(),
                steps.stream().map(StepRequest::toEntity)
                        .toList()
        );
    }
}
