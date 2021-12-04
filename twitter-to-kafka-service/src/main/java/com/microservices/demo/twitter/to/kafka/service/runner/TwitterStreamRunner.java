package com.microservices.demo.twitter.to.kafka.service.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.List;

@Component
@Slf4j
public class TwitterStreamRunner {

    /*
    Twitter stream object from twitter4j library
     */
    private TwitterStream twitterStream;

    /**
     * This method will create a Twitter stream thread that will listen to the streaming
     * tweets, filter them for the keywords and log them.
     */
    public void runAndFilter(List<String> twitterKeywords) {
        log.trace("[runAndFilter] called for twitter keywords {}", twitterKeywords);
        this.twitterStream = new TwitterStreamFactory().getInstance().addListener(
                new StatusAdapter() {
                    @Override
                    public void onStatus(Status status) {
                        log.info("Twitter status with text {}", status.getText());
                    }
                }
        ).filter(twitterKeywords.toArray(new String[0]));
    }

    @PreDestroy
    void onDestroy() {
        if (twitterStream != null) {
            log.info("Shutting down twitter stream");
            twitterStream.shutdown();
        }
    }

}
