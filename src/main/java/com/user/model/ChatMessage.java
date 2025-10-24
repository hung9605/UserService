package com.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ChatMessage extends BaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String message;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@NotNull
	Customer customer;

	
}
