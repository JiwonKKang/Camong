package com.bonjung.camong.experience.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;

    private MediaFile(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public static MediaFile from(String fileUrl) {
        return new MediaFile(
                fileUrl
        );
    }
}
