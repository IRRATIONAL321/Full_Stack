package com.resume.portal.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.resume.portal.dto.UserDTO;
import com.resume.portal.dto.accountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Indexed;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private accountType accountType;

    public UserDTO toDTO() {
        return new UserDTO(this.id, this.name, this.email, this.password, this.phone, this.accountType);
    }
}



