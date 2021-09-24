package net.futureorigin.architecture.sample.cola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ColaSampleServiceApp
 * <p></p>
 *
 * @author Leander Lee create on 2021/8/30.
 */
@SpringBootApplication(
        scanBasePackages = {
                "net.futureorigin.architecture.sample.cola"
        }
)
@EnableTransactionManagement
public class ColaSampleServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ColaSampleServiceApp.class, args);
    }
}
