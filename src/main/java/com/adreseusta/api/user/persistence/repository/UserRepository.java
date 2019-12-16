package com.adreseusta.api.user.persistence.repository;

import com.adreseusta.api.user.persistence.entity.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPasswordAndEmailOrPhoneNumber(@Length(min = 8, max = 16) String password, String email, String phoneNumber);
}
