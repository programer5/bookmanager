package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.*;
import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("JPA");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("david@naver.com");

        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());
    }

    @Test
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("JPA");

        Publisher publisher = new Publisher();
        publisher.setName("김영한");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books = " + bookRepository.findAll());
        System.out.println("publishers = " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("정민서");
        bookRepository.save(book1);

        System.out.println("publisher = " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();

//        publisherRepository.delete(book2.getPublisher());
        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);

        System.out.println("books = " + bookRepository.findAll());
        System.out.println("publishers = " + publisherRepository.findAll());
        System.out.println("book3-publisher = " + bookRepository.findById(1L).get().getPublisher());
    }

    @Test
    void queryTest() {
        System.out.println(" >>>> " +
                bookRepository.findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual(
                        "JPA", LocalDateTime.now().minusDays(1L),
                        LocalDateTime.now().minusDays(1L)
                ));

        System.out.println("findByNameRecently = "
                + bookRepository.findByNameRecently(
                        "JPA",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)));

        System.out.println(bookRepository.findBookNameAndCategory());

        bookRepository.findBookNameAndCategory(PageRequest.of(1, 2)).forEach(
                bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory()));

    }

    @Test
    void nativeQueryTest() {
//        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findAllCustom().forEach(System.out::println);
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            book.setCategory("IT전문서");
        }

        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println(bookRepository.updateCategories());
        System.out.println(bookRepository.findAllCustom());
    }

    @Test
    void converterTest() {
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("IT전문서적");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        System.out.println(bookRepository.findRawRecord().values());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser() {
        User user = new User();
        user.setEmail("david@naver.com");

        User user1 = userRepository.save(user);
        System.out.println("user1 = " + user1);
        System.out.println("userFind = " + userRepository.findByEmail(user1.getEmail()));
        return userRepository.findByEmail(user1.getEmail());
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이였어요.");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("김영한");
        return publisherRepository.save(publisher);
    }

}