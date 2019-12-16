package com.adreseusta.api.user.persistence.entity;

import com.adreseusta.api.common.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Audited
@Where(clause = "is_deleted = false")
@Table(name = "login")
@AllArgsConstructor
@NoArgsConstructor
public class Login extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "login_date_time", nullable = false)
    private LocalDateTime loginDateTime;

    @Column(name = "ip", nullable = false, length = 15)
    @Length(min = 7, max = 15)
    private String ip;
}
