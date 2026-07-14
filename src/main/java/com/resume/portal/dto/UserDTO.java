package com.resume.portal.dto;

import com.resume.portal.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private accountType accountType;
    private Long profileId;

    public User toEntity() {
        User user = new User(this.id, this.name, this.email, this.password, this.phone, this.accountType,this.profileId);

        return user;
    }


}
