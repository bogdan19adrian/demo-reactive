package ro.go.bogdanenache.demoreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class DemoReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoReactiveApplication.class, args);
    }

}
