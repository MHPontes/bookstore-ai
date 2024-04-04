package com.bookstore.ai.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookStoreAssistantController {

    private OpenAiChatClient chatClient;

    public BookStoreAssistantController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/informations")
    public String bookStoreChat(@RequestParam(value = "message",
            defaultValue = "Quais são os livros mais vendidos dos ultimos anos ?") String message) {
        return chatClient.call(message);
    }
}
