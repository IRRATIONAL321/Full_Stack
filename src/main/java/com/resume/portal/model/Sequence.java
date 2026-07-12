package com.resume.portal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Sequence {
    @Id
    private String id;
    private Long seq;
}
