package shop.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.dto.OrderDto;
import shop.entity.Address;
import shop.entity.Cart;
import shop.entity.Order;
import shop.entity.User;
import shop.service.AddressService;
import shop.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final OrderService orderService;
    private final AddressService addressService;

    @PostMapping("/checkout")
    public String order(@ModelAttribute OrderDto orderDto, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        Address address = (Address) session.getAttribute("selected_address");
        Cart cart = (Cart) session.getAttribute("cart");
        Order order = orderService.createOrder(orderDto, user, address, cart);
        redirectAttributes.addFlashAttribute("order", order);
        return "redirect:/order/success";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user != null) {
            List<Address> allByUserId = addressService.findAllByUserId(user.getId());
            model.addAttribute("addresses", allByUserId);
        }
        return "checkout";
    }

    @PostMapping("/checkout/address")
    public String chooseAddress(@RequestParam("selected_address") Long addressId, HttpSession httpSession) {
        Address byId = addressService.findById(addressId);
        httpSession.setAttribute("selected_address", byId);
        return "redirect:/checkout";
    }
}
