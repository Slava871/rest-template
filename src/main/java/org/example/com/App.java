package org.example.com;

import org.example.com.configuration.MyConfig;
import org.example.com.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        communication comm = context.getBean("communication", communication.class);

//        List<User> userList =  comm.getAllUsers();
//
//        System.out.println(userList);

//получаем cookies: JSESSIONID=E2066FF2E7F7A27A4E83B51E2417D631; Path=/; HttpOnly
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);

     String  cookie =  comm.getListUserByExchangeMethod(requestEntity);

//add user
        headers.add("Cookie", cookie);
        User sysUser = new User();
        sysUser.setId(3L);
        sysUser.setName("James");
        sysUser.setLastName("Brown");
        sysUser.setAge((byte)33);
        requestEntity = new HttpEntity<>(sysUser, headers);
        comm.addUserByExchangeMethod(requestEntity);

//update user
        sysUser.setName("Thomas");
        sysUser.setLastName("Shelby");
        requestEntity = new HttpEntity<>(sysUser, headers);
        comm.updateUserByExchangeMethod(requestEntity);

//elete

        comm.deleteUserByExchangeMethod(requestEntity, sysUser);

        String total  = "5ebfebe7cb975dfcf9";

    }
}
