package com.adreseusta.api.comment.persistence.entity;

import com.adreseusta.api.common.persistence.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Audited
@Where(clause = "is_deleted = false")
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "comment", length = 500, nullable = false)
    private String comment;

    @Lob
    @Column(name = "image", length = 1000000)
    private byte[] image;
}
