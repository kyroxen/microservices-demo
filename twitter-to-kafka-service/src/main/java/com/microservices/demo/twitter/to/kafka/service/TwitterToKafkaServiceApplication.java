package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
On slf4j:
Slf4j provides a facade to *any of the underlying logging framework.
So if you use slf4j, basically you are abstracting away the specific logging library.
In case you switch someday from logback to any other library, the code changes required
would be minimal like so: just switch up the config file and the maven dependency.
As opposed to changing all the static log variable types in *all of your java classes.
private static final Logger log = LoggerFactory.getLogger(TwitterElasticQueryWebClient.class);

On CommandLineRunner:
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.spring-application.command-line-runner
https://stackoverflow.com/questions/54265552/different-ways-to-run-custom-code-before-the-application-starts
 */
@Slf4j
@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private final TwitterToKafkaServiceConfig config;

    public TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfig config) {
        this.config = config;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Hello there! The twitter keywords are: {}", config.getTwitterKeywords());
    }
}
