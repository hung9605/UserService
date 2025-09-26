package com.user.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@DynamicUpdate
public class User extends BaseEntity{

    @Id
    @Column(name = "username", length = 50, nullable = false)
    String username;

    @Column(name = "password", length = 100)
    String password;

    @Column(name = "enabled")
    Boolean enabled;
    
    @Column(name = "email", length = 50)
    String email;
}