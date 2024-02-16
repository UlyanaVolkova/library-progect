package ru.volkova.libraryprogect.model;

import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType roleType;

    @OneToMany(mappedBy = "role")
    private Set<User> users;
}
