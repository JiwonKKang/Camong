package com.bonjung.camong.experience.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@ToString
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "experience", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Material> materials = new ArrayList<>();

    @OneToMany(mappedBy = "experience", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("sequence ASC")
    private List<Step> stepList = new ArrayList<>();


    private Experience(String title, String description, List<Material> materials, List<Step> stepList) {
        this.title = title;
        this.description = description;
        for (Material material : materials) {
            addMaterial(material);
        }
        for (Step step : stepList) {
            addStep(step);
        }
    }

    public static Experience of(String title, String description, List<Material> materials, List<Step> stepList) {
        return new Experience(title, description, materials, stepList);
    }

    public void addMaterial(Material material) {
        this.materials.add(material);
        material.setExperience(this);
    }

    public void addStep(Step step) {
        stepList.add(step);
        step.setExperience(this);
    }
}
