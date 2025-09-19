package com.user.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AuthorityId.class)
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authority implements Serializable {

    @Id
    @Column(name = "username", length = 50, nullable = false)
    String username;

    @Id
    @Column(name = "authority", length = 50, nullable = false)
    String authority;
}
