package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.EndsWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private EntityManager em;

    @Test
    void crud() {
        User david = userRepository.save(new User("david", "david@naver.com"));

        User user = userRepository.findById(david.getId()).orElseThrow(RuntimeException::new);
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

        User save = userRepository.save(user);

        User user1 = userRepository.findById(save.getId()).orElseThrow(RuntimeException::new);
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

    @Test
    void userRelationTest() {
        User user = new User();
        user.setName("david");
        user.setEmail("david@naver.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");

        userRepository.save(user);

        user.setEmail("daniel@gmail.com");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository
//                .findByUserId(userRepository.findByEmail("daniel@gmail.com").getId());

        List<UserHistory> result = userRepository.findByEmail("daniel@gmail.com").getUserHistories();

        result.forEach(System.out::println);

        System.out.println("UserHistory.getUser() " + userHistoryRepository.findAll().get(0).getUser());
    }

    @Test
    void embedTest() {
        userRepository.findAll().forEach(System.out::println);
        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시", "강남구", "강남대로", "1234"));
        user.setCompanyAddress(new Address("서울시", "성동구", "성수이로", "1234"));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("jspsp");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());

        userRepository.save(user2);

        em.clear();

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRawRecord().forEach(a -> System.out.println(a.values()));

        assertAll(
                () -> assertThat(userRepository.findById(2L).get().getHomeAddress()).isNull(),
                () -> assertThat(userRepository.findById(1L).get().getHomeAddress()).isInstanceOf(Address.class)
        );
    }
}