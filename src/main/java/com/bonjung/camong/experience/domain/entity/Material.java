package com.bonjung.camong.experience.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    @Setter
    @ToString.Exclude
    private Experience experience;

    private String name;

    private String quantity;

    private Material(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static Material of(String name, String quantity) {
        return new Material(name, quantity);
    }
}
