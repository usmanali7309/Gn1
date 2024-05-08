//package com.endpoints;
//
//import com.repositories.BankAccountRepository;
//import com.samplebanking.soap.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ws.server.endpoint.annotation.Endpoint;
//import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
//import org.springframework.ws.server.endpoint.annotation.RequestPayload;
//import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
//
//@Endpoint
//public class BankAccountEndpoint {
//
//    @Autowired
//    private BankAccountRepository bankAccountRepository;
//
//
//    @PayloadRoot(namespace = "http://samplebanking.com/soap",
//                localPart = "getBankAccountRequest")
//    @ResponsePayload
//    public GetBankAccountResponse getUserResponse(@RequestPayload GetBankAccountRequest request){
//
//        GetBankAccountResponse response = new GetBankAccountResponse();
//
//        response.setBankAccount(bankAccountRepository.findById(request.getCustomerId()).get());
//        return response;
//    }
//
////    @PayloadRoot(namespace = "http://samplebanking.com/soap",
////            localPart = "addBankAccountRequest")
////    @ResponsePayload
////    public AddBankAccountResponse addBankAccountResponse(@RequestPayload AddBankAccountRequest request){
////
////         AddBankAccountResponse response = new AddBankAccountResponse();
////         BankAccount bankAccount = new BankAccount();
////
////         User user = new User();
////         bankAccount.setCustomerId(request.getCustomerId());
////         bankAccount.setAccountNumber(request.getAccountNumber());
////         bankAccount.setBalance(request.getBalance());
////         bankAccount.setUser(request.getUser());
////
////        response.setBankAccount(bankAccountRepository.save(bankAccount));
////        return response;
////    }
//
//
//
//}
