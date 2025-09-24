package com.user.model;

import java.util.Date;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name="created_by",updatable = false)
    @CreatedBy
    String createdBy;
    @Column(name="created_at",updatable = false)
    @CreatedDate
    Date createdAt;
    @LastModifiedBy
    @Column(name = "updated_by")
    String updatedBy;
    @LastModifiedDate
    @Column(name = "updated_at")
    Date updatedAt;
}