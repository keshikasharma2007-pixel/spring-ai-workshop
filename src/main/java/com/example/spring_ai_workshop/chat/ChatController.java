package com.example.spring_ai_workshop.chat;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build(); // gives us an instance of chatClient
        // lets us talk to the LLM
    }

    @GetMapping("/chat") // REST endpoint
    public String chat() {
        return chatClient.prompt()
                //prompt sent to LLM
                .user("Tell me an interesting fact about Java")
                // blocking call - one time interaction
                .call()
                // string response from LLM
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> stream() {
        return chatClient.prompt()
                .user("I'm visiting Singapore soon, can you give me 10 places I must visit?")
                // continuous flow of data; data arrives in pieces over time
                .stream()
                .content();
    }

    @GetMapping("/joke")
    public ChatResponse joke() {
        return chatClient.prompt()
                .user("Tell me a dad joke about dogs")
                .call()
                // returns AI's output + metadata
                .chatResponse();
    }

}
