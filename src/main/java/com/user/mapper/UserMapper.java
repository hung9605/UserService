package com.user.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.user.dto.UserDto;
import com.user.model.User;
@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(source = "status", target = "enabled")
	User mapToModel(UserDto dto);
	@Mapping(source = "enabled", target = "status")
	@Mapping(target = "password", ignore = true)
	@Mapping(target = "roles", ignore = true)
	UserDto maptoDto(User model);	
	List<User> mapToModels(List<UserDto> dtos);
	List<UserDto> mapToDtos(List<User> users);
}