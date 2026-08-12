package com.example.spring_ai_workshop.tools.action;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// MUST use llama3.1
@RestController
public class TaskManagementController {

    private final ChatClient chatClient;
    private final TaskManagementTools taskManagementTools;

    public TaskManagementController(ChatClient.Builder builder, TaskManagementTools taskManagementTools) {
        this.chatClient = builder.build();
        this.taskManagementTools = taskManagementTools;
    }

    @GetMapping("/tasks")
    public String createTask(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .options(OllamaChatOptions.builder().model("llama3.1"))
                .tools(taskManagementTools)
                .call()
                .content();
    }
}
