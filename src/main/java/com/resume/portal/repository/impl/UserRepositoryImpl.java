package com.resume.portal.repository.impl;

import com.resume.portal.model.User;
import com.resume.portal.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Long> {
}
//test