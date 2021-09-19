package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    List<User> findUserByNameIs(String name);
    List<User> findUserByName(String name);
    List<User> findUserByNameEquals(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail (String email);

    User findUserByEmail (String email);

    User findSomethingByEmail(String email);

    User findFirst1ByName(String name);

//    User findTop1ByName(String name);

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreateAtAfter(LocalDateTime yesterday);

    List<User> findByIdAfter(Long id);

    List<User> findByCreateAtGreaterThan(LocalDateTime yesterday);

    List<User> findByCreateAtGreaterThanEqual(LocalDateTime yesterday);

    List<User> findByCreateAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<User> findByIdBetween(Long id1, Long id2);

    List<User> findByIdIsNotNull();

//    List<User> findByaddressesIsNotEmpty();

    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);

    List<User> findTop1ByName(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name);

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name, Sort sort);

}
