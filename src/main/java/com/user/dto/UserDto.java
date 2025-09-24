package com.user.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDto {
	
	String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	String password;
	String email;
	Boolean status;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	List<Role> roles;

}
