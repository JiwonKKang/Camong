package com.bonjung.camong.experience.domain.repository;

import com.bonjung.camong.experience.domain.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Long> {

    List<Step> findByExperience_Id(Long experienceId);

}
