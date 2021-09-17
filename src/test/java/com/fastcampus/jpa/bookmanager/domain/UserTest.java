package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void test() {
        User user = new User();
        user.setEmail("martin@fastcampus.com");
        user.setName("martin");
        System.out.println("user = " + user.toString());
    }
}