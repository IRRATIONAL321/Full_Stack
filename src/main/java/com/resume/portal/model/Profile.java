package com.resume.portal.model;

import com.resume.portal.dto.Certification;
import com.resume.portal.dto.Experience;
import com.resume.portal.dto.ProfileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    @ElementCollection
    private List<String> skills;
    @ElementCollection
    private List<Experience> experiences;
    @ElementCollection
    private List<Certification> certifications;

    public ProfileDTO toDTO() {
        return new ProfileDTO(this.id,this.email,this.jobTitle,this.company,this.location,this.about,this.skills,this.experiences,this.certifications);
    }
}
