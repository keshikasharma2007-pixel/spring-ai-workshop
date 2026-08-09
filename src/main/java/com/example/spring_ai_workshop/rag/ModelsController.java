package com.example.spring_ai_workshop.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;

@RestController
public class ModelsController {
    private final ChatClient chatClient;

    /*
    context from question is retrieved from a Vector Store and added to the prompt's user text
     */
    public ModelsController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
                .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .build();
    }

    @GetMapping("/rag/models")
    public String faq(@RequestParam(value = "message", defaultValue =
            "Give me a list of all the models from OpenAI along with their context window.") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }



}
