package Honeycloud.honey.web;


import Honeycloud.honey.entity.Honey;
import Honeycloud.honey.entity.HoneyPack;
import Honeycloud.honey.entity.Order;
import Honeycloud.honey.repository.HoneyRepository;
import Honeycloud.honey.repository.HoneyPackRepository;
import Honeycloud.honey.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.html.HTMLObjectElement;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
public class DesignHoneyController {

    private HoneyRepository honeyRepo;
    private UserRepository userRepo;


    public DesignHoneyController( HoneyRepository honeyRepo, UserRepository userRepo){
        this.honeyRepo = honeyRepo;
        this.userRepo = userRepo;

    }

    @GetMapping
    public String getDesignForm(Model model){
        addToModel(model);

        model.addAttribute("honeyPack", new HoneyPack());
        return "design";
    }

    @PostMapping()
    public String postDesignForm(@Valid HoneyPack honeyPack, Errors errors, Model model, HttpSession httpSession){
        if(errors.hasErrors()){
            addToModel(model);
            return "design";
        }

        httpSession.setAttribute("honeyPack",honeyPack);
        return "redirect:/orders";
    }

    private void addToModel(Model model){
        List<Honey> honeys = new ArrayList<>();
        honeyRepo.findAll().forEach(honeys::add);
        model.addAttribute("honeys",honeys);
    }
}
