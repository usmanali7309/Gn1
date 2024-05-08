package com.user.rest.services;

import com.user.rest.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    public UserDto createUser(UserDto userDto);

    public UserDto getUser(long userId);

    public List<UserDto> getAllUser();

    public UserDto updateUser(long userId,UserDto userDto);

    public  UserDto deleteUser(long userId);

}
