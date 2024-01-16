package com.roben.demo.serveces;

import com.roben.demo.domain.user.User;
import com.roben.demo.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationServicer {

    @Autowired
    private RestTemplate restTemplate;

    private final String URLemail= "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

    public void  sendNotification (User user, String message) throws Exception{
        String email= user.getEmail();
        NotificationDTO notificationRequest= new NotificationDTO( email, message);

        ResponseEntity<String> notificationResponse= restTemplate.postForEntity( URLemail, notificationRequest, String.class);

        if(  !(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("Erro en notification");
            throw new Exception( "Serviço fora  do ar ");
        }



    }


}
