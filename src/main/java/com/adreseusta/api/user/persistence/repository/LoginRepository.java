package com.adreseusta.api.user.persistence.repository;

import com.adreseusta.api.user.persistence.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
