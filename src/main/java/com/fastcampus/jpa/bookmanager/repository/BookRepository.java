package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "update book set category = 'none'", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

    @Query(value = "select b from Book b "
            + "where name = :name and createAt >= :createAt and updateAt >= :updateAt and category is null")
    List<Book> findByNameRecently(
            @Param("name") String name,
            @Param("createAt") LocalDateTime createAt,
            @Param("updateAt") LocalDateTime updateAt);

    @Query(value = "select b.name as name, b.category as category from Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

}
