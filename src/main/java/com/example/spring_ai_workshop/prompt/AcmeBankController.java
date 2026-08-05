package com.example.spring_ai_workshop.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {
    private final ChatClient chatClient;

    public AcmeBankController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @RequestMapping("/chat")
    public String chat(@RequestParam String message) {

        var systemInstructions = """
                You are a customer service assistant for AcmeBank.
                You can ONLY discuss:
                - Account balances and transactions
                - Branch locations and hours
                - General banking services
                
                If asked about anything else, ONLY respond with: "I can only help with banking-related questions."
                """;

        return chatClient.prompt()
                .user(message)
                // passing in a system message
                .system(systemInstructions)
                .call()
                .content();
    }



}
