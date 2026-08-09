package com.example.spring_ai_workshop.byod;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelComparison {
    private final ChatClient chatClient;

    public ModelComparison(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/models")
    public String models() {
        return chatClient.prompt()
                .user("can you give me an up to date list of popular large language models and " +
                        "their current context windows?")
                .call()
                .content();
    }

    // Prompt Stuffing
    @GetMapping("/models/stuff-the-prompt")
    public String modelsStuffThePrompt() {

        // static up-to-date information
        var system = """
                If you are asked about up to date language models and their context windows, here is
                some information to help you with your response:
                [
                    {"company": "OpenAI",   "model": "GPT-5.6",  "context_window_size": 1000000},
                    {"company": "OpenAI",   "model": "GPT-5.5",  "context_window_size": 1000000},
                    
                    {"company": "Anthropic",   "model": "Claude Fable 5",  "context_window_size": 1000000},
                    {"company": "Anthropic",   "model": "Claude Mythos 5",  "context_window_size": 1000000}
                ]
                """;

        return chatClient.prompt()
                .user("can you give me an up to date list of popular large language models and " +
                        "their current context windows?")
                .system(system)
                .call()
                .content();
    }
}
