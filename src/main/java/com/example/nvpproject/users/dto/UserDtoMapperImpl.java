package com.example.nvpproject.users.dto;

import com.example.nvpproject.users.entity.User;

public class UserDtoMapperImpl implements UserMapper{
    @Override
    public UserDto userToUserDto(User user) {
        if(user==null){
        return null;}
        UserDto userDto =new UserDto();
        userDto.setIdUser(user.getIdUser());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setTypeUser(user.getTypeUser());
        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        if(userDto==null){
            return null;}
        User user =new User();
        user.setIdUser(userDto.getIdUser());

        return user;
    }
}
