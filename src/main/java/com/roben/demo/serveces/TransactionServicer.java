package com.roben.demo.serveces;

import com.roben.demo.domain.transaction.Transaction;
import com.roben.demo.domain.user.User;
import com.roben.demo.dtos.TransactionDTO;
import com.roben.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionServicer {

    @Autowired
    private  UserServicer  userServicer;


    @Autowired
    private TransactionRepository repository;


    @Autowired
    private RestTemplate restTemplate;



    private final String URLautorizetion= "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

    @Autowired
    private  NotificationServicer notificationServicer;


    public  Transaction  createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userServicer.findByUser(transaction.senderId());
        User receiver= this.userServicer.findByUser(transaction.receiverId());
        userServicer.validateTransaction(sender,transaction.value());

         boolean isAuthorized=(this.authorizeTransaction(sender, transaction.value()));

         if(!isAuthorized){
            throw  new Exception("Transação não autorizada");

        }
        Transaction  newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());
        sender.setBanlance((sender.getBanlance().subtract(transaction.value())));

        receiver.setBanlance(receiver.getBanlance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userServicer.saveUser(sender);
        this.userServicer.saveUser(receiver);
        this.notificationServicer.sendNotification(sender,"Transação  realizada com suçesso");
        this.notificationServicer.sendNotification(receiver,"Transação  realizada com suçesso");
        return newTransaction;

    }


    public  boolean authorizeTransaction (User  user, BigDecimal  value){


        ResponseEntity<Map> autorizetionResponse= restTemplate.getForEntity(URLautorizetion, Map.class);
        if( autorizetionResponse.getStatusCode() == HttpStatus.OK){
            String message= (String) autorizetionResponse.getBody().get("message");
            return  "Autorizado".equalsIgnoreCase(message);
        }else  return  false;

    }


}
