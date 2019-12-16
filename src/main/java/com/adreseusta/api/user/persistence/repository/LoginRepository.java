package com.adreseusta.api.user.persistence.repository;

import com.adreseusta.api.user.persistence.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
