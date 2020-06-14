package org.example.dpostnov;

import lombok.NonNull;
import lombok.val;
import org.example.dpostnov.domain.Message;
import org.example.dpostnov.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required = false, defaultValue = "world") String name,
            Map<String, Object> model
    ){
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model){
        val messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(
            @NonNull @RequestParam String text,
            @NonNull @RequestParam String tag,
            Map<String, Object> model
    ){
        Message message = new Message(text, tag);
        messageRepo.save(message);
        val messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(
            @NonNull @RequestParam String filter,
            Map<String, Object> model
    ){
        Iterable<Message> messages;
        if(filter.isEmpty())
            messages = messageRepo.findAll();
        else
            messages = messageRepo.findByTag(filter);
        model.put("messages", messages);


        return "main";
    }
}
