package com.endpoints;

import com.repositories.UserRepository;
import com.samplebanking.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Endpoint
public class UserEndpoint {

//    this class for endpoint same as controller in rest service
 @Autowired
 private UserRepository userRepository;

// for add User
 @PayloadRoot(namespace = "http://samplebanking.com/soap",
 localPart = "addUserRequest")
 @ResponsePayload
 public AddUserResponse addUserRequest(@RequestPayload AddUserRequest request){

     AddUserResponse response = new AddUserResponse();

     User user = new User();
     user.setFirstName(request.getFirstName());
     user.setLastName(request.getLastName());
     user.setEmail(request.getEmail());
     user.setPhoneNumber(request.getPhoneNumber());
     user.setAddress(request.getAddress());

    response.setUser(userRepository.save(user));
    return response;

 }
// For get user
 @PayloadRoot(namespace = "http://samplebanking.com/soap",
            localPart = "getUserRequest")
 @ResponsePayload
 public GetUserResponse getUserResponse(@RequestPayload GetUserRequest request){

     GetUserResponse response = new GetUserResponse();

     response.setUser(userRepository.findById(request.getUserId()).get());
     return response;
 }

    @PayloadRoot(namespace = "http://samplebanking.com/soap",
            localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUserResponse(@RequestPayload UpdateUserRequest request){

        UpdateUserResponse response = new UpdateUserResponse();

        User user = userRepository.findById(request.getUserId()).get();

        response.setUser(user);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());

        response.setUser(userRepository.save(user));

        return response;
    }

    @PayloadRoot(namespace = "http://samplebanking.com/soap",
            localPart = "getAllUserRequest")
    @ResponsePayload
    public GetAllUserResponse getAllUserResponse(@RequestPayload GetAllUserRequest request){

        GetAllUserResponse response = new GetAllUserResponse();

        List<User> all = userRepository.findAll();
        response.setUser(all);
        return response;
    }

    @PayloadRoot(namespace = "http://samplebanking.com/soap",
    localPart = "deleteUserRequest")
    @ResponsePayload
    public DeleteUserResponse deleteUserResponse(@RequestPayload DeleteUserRequest request){
        DeleteUserResponse response = new DeleteUserResponse();

        userRepository.deleteById(request.getUserId());

        response.setMessage("User is deleted");

        return response;
    }

    @PayloadRoot(namespace = "http://samplebanking.com/soap",
            localPart = "GetTrackingNoRequest")
    @ResponsePayload
    public GetTrackingNoResponse getTrackingNoResponse(@RequestPayload GetTrackingNoRequest request){
        GetTrackingNoResponse response = new GetTrackingNoResponse();

        String[] inputParameters = request.getInputParameters();
        String s = inputParameters[0];
        String s1 = inputParameters[1];
        String s2 = inputParameters[2];
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);

// s2 --> Email sub(Avaya Email) s2 + '-' + uniqueID

        Random random = new Random();
        int uniqueID = 100000+random.nextInt(999999);
        s2 = "Email sub(Avaya Email) "+s2+"-"+uniqueID;

        List<String> responseList = new ArrayList<>();
        responseList.add(s2);
        responseList.add("98348021184");
        responseList.add("98348021185");
        responseList.add("98348021186");

        response.setTrackingNumbers(responseList);
        return response;
    }


}
