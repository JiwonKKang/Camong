package com.bonjung.camong.experience.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    @Setter
    @ToString.Exclude
    private Experience experience;

    private String line;


    private Step(Integer sequence, String line) {
        this.sequence = sequence;
        this.line = line;
    }

    public static Step of(Integer sequence, String line) {
        return new Step(sequence, line);
    }
}
