package com.bookstore.ai.controller;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

//    @GetMapping("/informations")
//    public ChatResponse bookStoreChat(@RequestParam(value = "message",
//            defaultValue = "Quais são os livros mais vendidos dos ultimos anos ?") String message) {         //por prompt retornado um ChatResponse do OpenAiChatClient
//        return chatClient.call(new Prompt(message));
//    }

    @GetMapping("/reviews")
    public String bookStoreReview(@RequestParam(value = "book", defaultValue = "Dom Quixote") String book) {
        PromptTemplate promptTemplate = new PromptTemplate("""
                Por favor me forneca um breve resumo do livro {book}\s
                ou informacoes do autor do livro""");        //a ideia e criar um promptTemplate com a pergunta que queremos fazer ao OpenAI ja pronta e direcionar a pergunta do usuario
        promptTemplate.add("book", book);
        return this.chatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

    @GetMapping("stream/informations")
    public Flux<String> bookStoreChatStream(@RequestParam(value = "message",
            defaultValue = "Quais são os livros mais vendidos dos ultimos anos ?") String message) {
        return chatClient.stream(message);
    }
}
