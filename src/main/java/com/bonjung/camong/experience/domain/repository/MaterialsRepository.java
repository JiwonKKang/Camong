package com.bonjung.camong.experience.domain.repository;

import com.bonjung.camong.experience.domain.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialsRepository extends JpaRepository<Material, Long> {
}
