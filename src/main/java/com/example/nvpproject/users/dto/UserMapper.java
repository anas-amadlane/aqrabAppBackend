package com.example.nvpproject.users.dto;

import com.example.nvpproject.users.entity.User;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE ,componentModel = "spring")
public interface UserMapper {

    static UserMapper getInstance(){
        return Mappers.getMapper(UserMapper.class);
    }

    UserDto userToUserDto(User user );
    User userDtoToUser(UserDto userDto);

}
