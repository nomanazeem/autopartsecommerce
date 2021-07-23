package nazeem.autoparts.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("nazeem.autoparts.library.model")
@EnableJpaRepositories("nazeem.autoparts.library.repository")
   public class AppClientMain {
    public static void main(String[] args) {
        SpringApplication.run(AppClientMain.class, args);
    }
}