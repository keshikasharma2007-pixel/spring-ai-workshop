package com.example.spring_ai_workshop.evals;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ChatClient chatClient;

    public ReviewService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultOptions(OllamaChatOptions.builder().temperature(0.1d)) // temperature
                // determines creativity level (0.1 is low creativity, very deterministic ->
                // good for testing)
                .build();
    }

    public Sentiment classifySentiment(String review) {
        String systemPrompt = """
                Classify the sentiment of the following text as POSITIVE, NEGATIVE, or NEUTRAL. \
                Your response must be only one of these three words.
                """;
        return chatClient.prompt()
                .system(systemPrompt)
                .user(review)
                .call()
                .entity(Sentiment.class);
    }
}
