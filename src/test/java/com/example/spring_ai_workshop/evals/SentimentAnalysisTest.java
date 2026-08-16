package com.example.spring_ai_workshop.evals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SentimentAnalysisTest {
    @Autowired
    ReviewService reviewService;

    @Test
    void testPositiveSentiment() {
        String positiveReview = "I absolutely loved the hotel, it was amazing!";
        Sentiment sentiment = reviewService.classifySentiment(positiveReview);

        assertEquals(Sentiment.POSITIVE, sentiment, "The sentiment should be classified as " +
                "positive");
    }

    @Test
    void testNegativeSentiment() {
        String negativeReview = "Thi is the worst experience I've ever had. The product is " +
                "terrible and broke immedieately.";
        Sentiment sentiment = reviewService.classifySentiment(negativeReview);
        assertEquals(Sentiment.NEGATIVE, sentiment, "The sentiment should be classified as " +
                "NEGATIVE");
    }

    @Test
    void testNeutralSentiment() {
        String neutralReview = "The product is okay. It does what i says but nothing more.";
        Sentiment sentiment = reviewService.classifySentiment(neutralReview);
        assertEquals(Sentiment.NEUTRAL, sentiment, "The sentiment should be classified as NEUTRAL");
    }
}
