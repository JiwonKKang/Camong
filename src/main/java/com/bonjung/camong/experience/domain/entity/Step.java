package com.bonjung.camong.experience.domain.entity;

import com.bonjung.camong.common.BaseTimeEntity;
import com.bonjung.camong.experience.api.request.StepCreateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Step extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sequence;

    private String title;

    private String line;

    @ManyToOne
    private Experience experience;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MediaFile image;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private MediaFile voice;

    private long duration;

    private Step(Integer sequence, String title, String line, Experience experience, MediaFile image, MediaFile voice, long duration) {
        this.sequence = sequence;
        this.title = title;
        this.line = line;
        this.experience = experience;
        this.image = image;
        this.voice = voice;
        this.duration = duration;
    }

    public static Step of(StepCreateRequest request, Experience experience, MediaFile image, MediaFile voiceFile) {
        return new Step(
                request.sequence(),
                request.title(),
                request.line(),
                experience,
                image,
                voiceFile,
                request.duration()
        );
    }
}
