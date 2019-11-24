package Honeycloud.honey.web;

import Honeycloud.honey.entity.Honey;
import Honeycloud.honey.entity.HoneyPack;
import Honeycloud.honey.entity.Order;
import Honeycloud.honey.repository.HoneyPackRepository;
import Honeycloud.honey.repository.HoneyRepository;
import Honeycloud.honey.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;


@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private static List<Honey> honeys = new ArrayList<>();

    private HoneyPackRepository honeyPackRepo;

    public OrderController( HoneyPackRepository honeyPackRepo){
        this.honeyPackRepo = honeyPackRepo;
    }

    @GetMapping
    public String getOrderForm(Model model, HttpSession httpSession){

        Enumeration<String> allAttr = httpSession.getAttributeNames();


        if(!allAttr.hasMoreElements()){
            String str = "Вы еще ничего не добавили";
            model.addAttribute("string",str);
        }
        else{
            while (allAttr.hasMoreElements()){

                String attrName = allAttr.nextElement();
                if(attrName.equals("honeyPack")){
                HoneyPack honeyPack = (HoneyPack) httpSession.getAttribute(attrName);
                honeys.addAll(honeyPack.getHoneys());
                httpSession.removeAttribute(attrName);

                }
             model.addAttribute("order", new Order());
            }
            model.addAttribute("honeys",honeys);
            httpSession.setAttribute("honeys",honeys);

            if(!allAttr.hasMoreElements()) {
                String prosto = "prosto";
                httpSession.setAttribute("prosto", prosto);
            }
        }
        return "orderForm";
    }
    @PostMapping()
    public String postDesignForm( @ModelAttribute Order order, HttpSession httpSession){

        List<Honey> list = (List<Honey>) httpSession.getAttribute("honeys");
        HoneyPack honeyPack = new HoneyPack();
        honeyPack.setHoneys(list);
        HoneyPack saved = honeyPackRepo.save(honeyPack);
        order.addDesign(saved);

        httpSession.setAttribute("deliveryHoneyPack",honeyPack);
        httpSession.setAttribute("order",order);

        return "redirect:/delivery";
    }


}
