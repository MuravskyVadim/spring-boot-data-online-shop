package com.example.onlineshop.controller;

import com.example.onlineshop.model.Basket;
import com.example.onlineshop.model.Code;
import com.example.onlineshop.model.Order;
import com.example.onlineshop.model.User;
import com.example.onlineshop.service.interfaces.BasketService;
import com.example.onlineshop.service.interfaces.CodeService;
import com.example.onlineshop.service.interfaces.OrderService;
import com.example.onlineshop.service.interfaces.MailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class OrderController {

    private final Logger logger = Logger.getLogger(OrderController.class);
    private final OrderService orderService;
    private final BasketService basketService;
    private final MailService mailService;
    private final CodeService codeService;

    @Autowired
    public OrderController(OrderService orderService, BasketService basketService,
                           MailService mailService, CodeService codeService) {
        this.orderService = orderService;
        this.basketService = basketService;
        this.mailService = mailService;
        this.codeService = codeService;
    }

    @PostMapping("/checkout")
    public String sendCode(
            @AuthenticationPrincipal User user,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("city") String city,
            @RequestParam("street") String street,
            @RequestParam("houseNumber") String houseNumber,
            @RequestParam("phoneNumber") String phoneNumber,
            HttpServletRequest request,
            ModelMap model) {
        try {
            if (!firstName.isEmpty() && !lastName.isEmpty() && !city.isEmpty()
                    && !street.isEmpty() && !houseNumber.isEmpty() && !phoneNumber.isEmpty()) {
                Code code = new Code(user);
                code.generateCode();
                codeService.saveCode(code);
                mailService.sendConfirmCode(code);
                Optional<Basket> basketByUserId = basketService.getBasketByUserId(user.getId());
                if (basketByUserId.isPresent()) {
                    Order order = new Order(basketByUserId.get(), user, code,
                            firstName, lastName, city, street, houseNumber, phoneNumber);
                    HttpSession session = request.getSession();
                    session.setAttribute("order", order);
                    return "code";
                } else {
                    logger.warn("Basket by user id " + user.getId() + " not exist");
                }
            } else {
                model.addAttribute("message", "All fields must be filled!!!");
            }
        } catch (NullPointerException e) {
            model.addAttribute("message", "Wrong data");
        }
        return "redirect:/user/checkout";
    }

    @PostMapping("/code")
    public ModelAndView confirmCode(
            ModelMap model,
            @RequestParam("code") String userCode,
            HttpServletRequest request) {
        Order order = (Order) request.getSession().getAttribute("order");
        try {
            if (Objects.nonNull(order) && userCode.equals(order.getCode().getValue())) {
                Long orderId = orderService.addOrder(order);
                model.addAttribute("message",
                        "Order #" + orderId + " successfully placed");
                return new ModelAndView("code");
            } else {
                model.addAttribute("message", "Incorrect code!!!");
                return new ModelAndView("code");
            }
        } catch (NullPointerException e) {
            model.addAttribute("message", "Wrong data!!!");
            return new ModelAndView("code");
        }
    }
}
