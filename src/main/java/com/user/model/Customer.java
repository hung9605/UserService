package com.user.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Customer extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	Integer id;
	
	@NotBlank(message = "Username is required")
	String username;
	
	@OneToMany
	@JoinColumn(name = "customer_id")
	List<ChatMessage> messages;

}
