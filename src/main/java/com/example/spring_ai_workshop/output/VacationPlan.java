package com.example.spring_ai_workshop.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class VacationPlan {
    private final ChatClient chatClient;

    public VacationPlan(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/vacation/unstructured")
    // won't know what format we'll get the output in
    public String unstructured() {
        return chatClient.prompt()
                .user("I want to plan a trip to Singapore. Give me a list of things to do.")
                .call()
                .content();
    }

    @GetMapping("/vacation/structured")
    public Itinerary structured() {
        return chatClient.prompt()
                .user("I want to plan a trip to Singapore. Give me a list of things to do.")
                .call()
                // entity - sends user message + format (structured output)
                .entity(Itinerary.class);
    }


}
