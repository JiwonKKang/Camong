package com.bonjung.camong.experience.domain.entity;

import com.bonjung.camong.common.BaseTimeEntity;
import com.bonjung.camong.experience.api.request.ExperienceCreateRequest;
import com.bonjung.camong.experience.api.request.StepCreateRequest;
import com.bonjung.camong.experience.api.request.StepUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;
import java.util.function.Consumer;

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

    private Boolean isImage;

    private String videoUrl;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Experience experience;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MediaFile image;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MediaFile voice;

    private Long duration;

    public Step(Integer sequence, String title, String line, Boolean isImage, Experience experience, MediaFile image, MediaFile voice, long duration) {
        this.sequence = sequence;
        this.title = title;
        this.line = line;
        this.isImage = isImage;
        this.experience = experience;
        this.image = image;
        this.voice = voice;
        this.duration = duration;
    }

    public Step(Integer sequence, String title, Boolean isImage, String videoUrl, Experience experience) {
        this.sequence = sequence;
        this.title = title;
        this.isImage = isImage;
        this.videoUrl = videoUrl;
        this.experience = experience;
    }

    public void updateVideoUrl(String videoUrl) {
        if (Objects.nonNull(videoUrl)) this.videoUrl = videoUrl;
    }

    public void decreaseSequence() {
        this.sequence--;
    }

    public void updateSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void update(StepUpdateRequest request) {
        this.title = request.title();
        this.line = request.line();
        this.duration = request.duration();
    }

    public void updateImageFile(MediaFile imageFile) {
        this.image = imageFile;
    }

    public void updateVoiceFile(MediaFile voiceFile) {
        this.voice = voiceFile;
    }

}
