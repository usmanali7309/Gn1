package com.user.rest.controller;

import com.user.rest.dto.UserDto;
import com.user.rest.response.Response;
import com.user.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("user/api")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){

        Response response = null;

         UserDto user = userService.createUser(userDto);
         if(user!=null){
             response  = new Response("200","Success");
         }

//         return new ResponseEntity<>("Bad request",HttpStatus.BAD_REQUEST);
         return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") long userId){

        UserDto user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){

        List<UserDto> user = userService.getAllUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") long userId){

        UserDto user = userService.deleteUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") long userId,@RequestBody UserDto userDto){

        UserDto user = userService.updateUser(userId,userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }






}
