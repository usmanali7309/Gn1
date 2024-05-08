package com.user.rest.mapper;

import com.user.rest.dto.UserDto;
import com.user.rest.entities.User;

public class Mapper {


    public static User DtoToEntity(UserDto userDto) {

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());

        return user;

    }

    public static UserDto EntityToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setUserID(user.getUserID());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());

        return userDto;


    }
}