package com.example.webpastebinspringboot.controller;

import com.example.webpastebinspringboot.repositories.MessageRepository;
import com.example.webpastebinspringboot.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(Model model, @RequestParam String text, @RequestParam String tag) {
        Message message = new Message(text, tag);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String messageFilter(@RequestParam String filter, Model model) {
        if (filter.isEmpty()) {
            model.addAttribute("messages", messageRepository.findAll());
        } else {
            List<Message> byTag = messageRepository.findByTag(filter);

            model.addAttribute("messages", byTag);
        }
        return "main";
    }

}