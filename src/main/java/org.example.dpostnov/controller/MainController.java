package org.example.dpostnov.controller;

import lombok.NonNull;
import lombok.val;
import org.example.dpostnov.domain.Message;
import org.example.dpostnov.domain.User;
import org.example.dpostnov.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    public MainController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    private final MessageRepo messageRepo;



    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("main")
    public String add(
            @AuthenticationPrincipal User user,
            @NonNull @RequestParam String text,
            @NonNull @RequestParam String tag,
            Model model
    ){
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        val messages = messageRepo.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

}
