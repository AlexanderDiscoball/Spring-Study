package Honeycloud.honey.web;

import Honeycloud.honey.entity.Honey;
import Honeycloud.honey.entity.HoneyPack;
import Honeycloud.honey.entity.Order;
import Honeycloud.honey.entity.User;
import Honeycloud.honey.repository.HoneyPackRepository;
import Honeycloud.honey.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/delivery")
@SessionAttributes({"order","deliveryHoneyPack"})
public class DeliveryController {

    private OrderRepository orderRepo;

    @Autowired
    public DeliveryController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping()
    public String orderForm(@ModelAttribute Order order, Model model, @ModelAttribute HoneyPack deliveryHoneyPack) {

        model.addAttribute("honeys",deliveryHoneyPack.getHoneys());
        model.addAttribute("order",order);
        System.out.println(order);
        order.getHoneysPack().forEach(System.out::println);

        return "delivery";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                               @ModelAttribute HoneyPack honeyPack, Model model) {
        if(errors.hasErrors()){
            return "delivery";
        }

        orderRepo.save(order);
        sessionStatus.setComplete();

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
