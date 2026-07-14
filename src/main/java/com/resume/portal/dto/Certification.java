package com.resume.portal.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Certification {
    private String title;
    private String issuer;
    private LocalDate issueDate;
    private String certificateId;

}
