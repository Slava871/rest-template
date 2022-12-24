package org.example.com;

import org.example.com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/*
     Список URL для операций и типы запросов:

Получение всех пользователей - …/api/users ( GET )
Добавление пользователя - …/api/users ( POST )
Изменение пользователя - …/api/users ( PUT )
Удаление пользователя - …/api/users /{id} ( DELETE )
 */


@Component
public class communication {

    String cookie;


    private final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    private RestTemplate restTemplate;

    //trgulov
    public List<User> getAllUsers(){
        ResponseEntity<List<User>> responseEntity  =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> userList = responseEntity.getBody();
        return userList;
    }



    public User getUserWithId(Long id){
        ResponseEntity<List<User>> responseEntity  =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> userList = responseEntity.getBody();
        return null;
    }

    public void SaveUpdateUser(User user){

    }

    public void deleteUser(int id){

    }

    public   String getListUserByExchangeMethod(HttpEntity<User> requestEntity) {
        ResponseEntity<List> responseEntity = restTemplate.exchange(URL ,
                HttpMethod.GET,
                requestEntity,
                List.class);

        HttpHeaders responseHeaders = responseEntity.getHeaders();  // взяли куки
        cookie = responseHeaders.getFirst("Set-Cookie");
        System.out.println(cookie);
        return cookie;

    }



    //https://www.youtube.com/watch?v=RHplGVRKwlc&list=PLq3uEqRnr_2FuTmCBjb4Br5eunU693prt&index=4
     //add new user
    public  void addUserByExchangeMethod(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST,
                requestEntity,
                String.class);
//
        System.out.println(responseEntity.getBody());

    }

    public   void updateUserByExchangeMethod(HttpEntity<User> requestEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.PUT,
                requestEntity,
                String.class);

        System.out.println(responseEntity.getBody());
    }

    public void deleteUserByExchangeMethod(HttpEntity<User> requestEntity, User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + user.getId(),
                HttpMethod.DELETE,
                requestEntity,
                String.class);
        System.out.println(responseEntity.getBody());
    }

}
