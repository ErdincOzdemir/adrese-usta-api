package com.adreseusta.api.user.persistence.entity;

import com.adreseusta.api.common.persistence.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Audited
@Where(clause = "is_deleted = false")
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 10, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 16)
    @Length(min = 8, max = 16)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Login> loginList = new HashSet<>();
}
