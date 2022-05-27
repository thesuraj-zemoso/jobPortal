package com.project.jobportal.entity;

import javax.persistence.*;

@Entity
@Table(name="skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="skill_id")
    private long skillId;

    @Column(name="skill_name")
    private String skillName;
}
