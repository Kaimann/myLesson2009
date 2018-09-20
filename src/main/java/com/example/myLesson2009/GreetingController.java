package com.example.myLesson2009;

import com.example.myLesson2009.domain.Message;
import com.example.myLesson2009.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Model model)
    {
        model.addAttribute("name", name);

        return "greeting";
    }
    @GetMapping
    public String main(String name, Model model){
        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }
    @PostMapping
    public String add(@RequestParam String text, @RequestParam String address, @RequestParam Integer price,String name, Model model){
        Message message = new Message(text, address, price);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter,String name, Model model){
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByAddress(filter);
        }else {
            messages = messageRepo.findAll();
        }
        model.addAttribute("messages", messages);

        return "main";
    }

}
