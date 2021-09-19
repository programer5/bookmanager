package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud() {
        userRepository.save(new User("david", "david@naver.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("divid@gmail.com");

        userRepository.save(user);
    }

    @Test
    void select() {
        System.out.println(userRepository.findByName("david"));

        userRepository.save(new User("david", "david@naver.com"));

        System.out.println("findByEmail = " + userRepository.findByEmail("david@naver.com"));
        System.out.println("getByEmail = " + userRepository.getByEmail("david@naver.com"));
        System.out.println("readByEmail = " + userRepository.readByEmail("david@naver.com"));
        System.out.println("queryByEmail = " + userRepository.queryByEmail("david@naver.com"));
        System.out.println("searchByEmail = " + userRepository.searchByEmail("david@naver.com"));
        System.out.println("streamByEmail = " + userRepository.streamByEmail("david@naver.com"));
        System.out.println("findUserByEmail = " + userRepository.findUserByEmail("david@naver.com"));
        System.out.println("findSomethingByEmail = " + userRepository.findSomethingByEmail("david@naver.com"));
        System.out.println("findFirstByName = " + userRepository.findFirst1ByName("david"));
        System.out.println("findTop1ByName = " + userRepository.findTop1ByName("david"));

    }
}