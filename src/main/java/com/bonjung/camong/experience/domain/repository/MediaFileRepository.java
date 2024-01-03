package com.bonjung.camong.experience.domain.repository;

import com.bonjung.camong.experience.domain.entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFileRepository extends JpaRepository<MediaFile, Long> {
}
