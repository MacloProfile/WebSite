package com.example.webpastebinspringboot.controller;

import com.example.webpastebinspringboot.repositories.MessageRepository;
import com.example.webpastebinspringboot.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    //for downloading files
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages;

        //check filter status
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(Model model, @RequestParam String text, @RequestParam String tag,
                      @RequestParam("file") MultipartFile file) throws IOException {

        Message message = new Message(text, tag);
        if (file != null) {
            File file1 = new File(uploadPath);

            //secure from a non-existent directory
            if (!file1.exists())
                file1.mkdir();

            String uuid = UUID.randomUUID().toString();
            String resultName = uuid + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultName));

            message.setFilename(resultName);
        }

        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }

}
