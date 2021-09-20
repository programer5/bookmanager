package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

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

//        userRepository.save(new User(1L,"david", "david@naver.com", LocalDateTime.now(), LocalDateTime.now()));

//        System.out.println("findByEmail = " + userRepository.findByEmail("david@naver.com"));
//        System.out.println("getByEmail = " + userRepository.getByEmail("david@naver.com"));
//        System.out.println("readByEmail = " + userRepository.readByEmail("david@naver.com"));
//        System.out.println("queryByEmail = " + userRepository.queryByEmail("david@naver.com"));
//        System.out.println("searchByEmail = " + userRepository.searchByEmail("david@naver.com"));
//        System.out.println("streamByEmail = " + userRepository.streamByEmail("david@naver.com"));
//        System.out.println("findUserByEmail = " + userRepository.findUserByEmail("david@naver.com"));
//        System.out.println("findSomethingByEmail = " + userRepository.findSomethingByEmail("david@naver.com"));
//        System.out.println("findFirstByName = " + userRepository.findFirst1ByName("david"));
//        System.out.println("findTop1ByName = " + userRepository.findTop1ByName("david"));
//        System.out.println("findByEmailAndName = " + userRepository.findByEmailAndName("david@naver.com", "david"));
//        System.out.println("findByEmailOrName = " + userRepository.findByEmailOrName("david@naver.com", "david"));
//        System.out.println("findByCreateAtAfter = " + userRepository.findByCreateAtAfter(LocalDateTime.now().minusDays(1L)));
//        System.out.println("findByIdAfter = " + userRepository.findByIdAfter(1L));
//        System.out.println("findByCreateAtGreaterThan = " + userRepository.findByCreateAtGreaterThan(LocalDateTime.now().minusDays(1L)));
//        System.out.println("findByCreateAtGreaterThanEqual = " + userRepository.findByCreateAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
//        System.out.println("findByCreateAtBetween = " + userRepository.findByCreateAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
//        System.out.println("findByIdBetween = " + userRepository.findByIdBetween(1L, 2L));
        System.out.println("findByIdIsNotNull = " + userRepository.findByIdIsNotNull());
//        System.out.println("findByaddressesIsNotEmpty = " + userRepository.findByaddressesIsNotEmpty());
        System.out.println("findByNameIn = " + userRepository.findByNameIn(Lists.newArrayList("david", "martin")));
        System.out.println("findByNameEndingWith = " + userRepository.findByNameEndingWith("id"));
        System.out.println("findByNameContains = " + userRepository.findByNameContains("av"));
        System.out.println("findByNameStartingWith = " + userRepository.findByNameStartingWith("da"));
        System.out.println("findByNameLike = " + userRepository.findByNameLike("%dav%"));

    }

    @Test
    void pagingAndSortingTest() {
//        userRepository.save(new User(1L,"david", "david@naver.com", LocalDateTime.now(), LocalDateTime.now()));
        System.out.println("findTop1ByName = " + userRepository.findTop1ByName("david"));
        System.out.println("findTop1ByNameOrderByIdDesc = " + userRepository.findTop1ByNameOrderByIdDesc("david"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc = " + userRepository.findFirstByNameOrderByIdDescEmailAsc("david"));
        System.out.println("findFirstByName = " + userRepository.findFirstByName("david", Sort.by(Sort.Order.desc("id"))));
        System.out.println("findByName = " + userRepository.findByName("david",
                PageRequest.of(1, 1, Sort.by(Sort.Order.desc("Id")))).getContent());
    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();
        user.setName("martin");
        user.setEmail("martin@naver.com");

        userRepository.save(user);

        User user1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user1.setName("marrrrrrtin");

        userRepository.save(user1);
    }

    @Test
    void enumTest() {
        User user = new User();
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("martin@naver.com");
        user.setName("martin");

        userRepository.save(user);

        user.setName("david");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

}