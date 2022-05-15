package rentABike.web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rentABike.model.Order;
import rentABike.model.User;
import rentABike.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getFavouritesPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        Order order = this.orderService.getActiveFavourites(username);
        model.addAttribute("rent", this.orderService.listAll(order.getId()));
        model.addAttribute("bodyContent","order");
        return "master-template";
    }

    @PostMapping("/add/{id}")
    public String addToFavourites(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            Order order = this.orderService.addToFavourites(user.getUsername(), id);
            return "redirect:/order";
        }catch (RuntimeException exception) {
            return "redirect:/order?error=" + exception.getMessage();
        }
    }
    @GetMapping("/allOrders")
    public String getAllOrders(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("orders", this.orderService.listAllOrders());
        //model.addAttribute("accessories", this.orderService.listAllOrdersAccessories());
        model.addAttribute("bodyContent","allOrders");
        return "master-template";
    }
}
