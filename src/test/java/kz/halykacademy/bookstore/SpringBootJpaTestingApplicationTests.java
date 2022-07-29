package kz.halykacademy.bookstore;

import kz.halykacademy.bookstore.store.interfaces.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
class SpringBootJpaTestingApplicationTests {

    @Autowired

    @Test
    void contextLoads() {

    }
}
