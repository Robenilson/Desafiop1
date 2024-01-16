package com.roben.demo.serveces;

import com.roben.demo.domain.user.User;
import com.roben.demo.domain.user.UserType;
import com.roben.demo.dtos.UserDTO;
import com.roben.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServicer {

    @Autowired
    private UserRepository  repository;

    public   void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if ( sender.getUserType() == UserType.MERCHANT){
            throw  new Exception(" Usuario  do tipo Logista não  está autorizado  a fazer essa transição");
        }
        if ( sender.getBanlance().compareTo(amount)< 0 ){
            throw new Exception("Saldo Insuficiente");
        }
    }

    public  User findByUser( Long id) throws Exception{
       return this.repository.findUserById(id).orElseThrow (()->new Exception("USuario não Encontrado"));
    }


    public   User createUser(UserDTO user){
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;

    }

    public List<User> getAllUser(){
        return this.repository.findAll();
    }

    public void saveUser( User user){
        this.repository.save(user);
    }

}
