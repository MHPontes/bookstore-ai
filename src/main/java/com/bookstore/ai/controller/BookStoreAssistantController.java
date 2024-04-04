package com.bookstore.ai.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookStoreAssistantController {

    private OpenAiChatClient chatClient;

    public BookStoreAssistantController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }
}
