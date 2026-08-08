package com.example.spring_ai_workshop.memory;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;



@RestController
public class MemoryController {
    private final ChatClient chatClient;

    public MemoryController(ChatClient.Builder builder, ChatMemory chatMemory) {
        // allows Ollama to build a chat memory
        this.chatClient = builder
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    @GetMapping("/memory")
    public String memory(@RequestParam String message,
                         @RequestParam(defaultValue = "default-conversation") String conversationId) {
        return chatClient.prompt()
                .user(message)
                .advisors(u -> u.param(CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}
