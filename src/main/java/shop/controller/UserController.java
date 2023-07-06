package shop.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.dto.NewAddressDto;
import shop.entity.Address;
import shop.entity.User;
import shop.service.AddressService;

import java.util.List;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AddressService addressService;

    @GetMapping("/address")
    public String findAddresses(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            List<Address> addresses = addressService.findAllByUserId(user.getId());
            model.addAttribute("addresses", addresses);
        }
        return "user/address";
    }

    @PostMapping("/address")
    public String addAddress(@ModelAttribute NewAddressDto newAddressDto, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        addressService.saveAddress(newAddressDto, user);
        return "redirect:/user/address";
    }

    // Implementuj inne metody kontrolera dla logowania, zarządzania profilem itp.
}