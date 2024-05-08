package com.user.rest.services.impl;

import com.user.rest.dto.UserDto;
import com.user.rest.entities.User;
import com.user.rest.mapper.Mapper;
import com.user.rest.repositories.UserRepository;
import com.user.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = Mapper.DtoToEntity(userDto);


        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateKeyException("Email already exists: " + user.getEmail());
        }

        User savedUser = userRepository.save(user);
        UserDto userDto1 = Mapper.EntityToDto(savedUser);
        return userDto1;

    }

    @Override
    public UserDto getUser(long userId) {

        User user = userRepository.findById(userId).get();
        UserDto userDto = Mapper.EntityToDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> allUser = userRepository.findAll();

        List<UserDto> collect = allUser.stream().map(e -> Mapper.EntityToDto(e)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public UserDto updateUser(long userId, UserDto userDto) {

        User user = userRepository.findById(userId).get();

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());

        User updatedUser = userRepository.save(user);
        UserDto userDto1 = Mapper.EntityToDto(updatedUser);

        return userDto1;
    }

    @Override
    public UserDto deleteUser(long userId) {
        User user = userRepository.findById(userId).get();

        if(user!=null){
            userRepository.deleteById(userId);
        }

        UserDto userDto = Mapper.EntityToDto(user);
        return userDto;
    }
}
