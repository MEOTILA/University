package org.example;


import org.example.entity.User;
import org.example.service.service.impl.UserServiceImpl;

public class Main {
    static UserServiceImpl userService = new UserServiceImpl();
    public static void main(String[] args) {

        User user3 = new User (null,"Bell", "Balla", "Bell" , "123456",
                123457890L,1234567891L);
        userService.save(user3);
    }
}