package nazeem.autoparts.client;

/*
    Created By: noman azeem
    Contact: syed.noman.azeem@gmail.com
*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("nazeem.autoparts.library.model")
@EnableJpaRepositories("nazeem.autoparts.library.repository")
   public class AppClientMain {
    public static void main(String[] args) {
        SpringApplication.run(AppClientMain.class, args);
    }
}