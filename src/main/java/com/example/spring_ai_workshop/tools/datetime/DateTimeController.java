package com.example.spring_ai_workshop.tools.datetime;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.ollama.api.OllamaChatOptions;

// will only work with llama 3.1
@RestController // must ALWAYS have this for a controller
public class DateTimeController {
    private final ChatClient chatClient;

    public DateTimeController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    // had to download llama3.1 since 3 doesn't support @Tool
    @GetMapping("/tools")
    public String tools() {
        return chatClient.prompt()
                .user("what is tomorrow's date?")
                .options(OllamaChatOptions.builder().model("llama3.1"))
                .tools(new DateTimeTools())
                .call()
                .content();
    }
}
