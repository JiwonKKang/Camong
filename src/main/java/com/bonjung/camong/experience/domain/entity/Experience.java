package com.bonjung.camong.experience.domain.entity;

import com.bonjung.camong.common.BaseTimeEntity;
import com.bonjung.camong.experience.api.request.ExperienceCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Experience extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MediaFile mainImage;

    @OneToMany(mappedBy = "experience", fetch = FetchType.LAZY)
    private List<Step> steps = new ArrayList<>();


    private Experience(String title, MediaFile mainImage) {
        this.title = title;
        this.mainImage = mainImage;
    }

    public static Experience of(ExperienceCreateRequest request, MediaFile imageFile) {
        return new Experience(
                request.title(),
                imageFile
        );
    }

    public int countSteps() {
        return steps.size();
    }

    public void update(ExperienceCreateRequest request) {
        this.title = request.title();
    }

    public void updateImage(MediaFile imageFile) {
        this.mainImage = imageFile;
    }
}
