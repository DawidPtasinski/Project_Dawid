package shop.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shop.dto.UserDto;
import shop.entity.User;
import shop.service.CartService;
import shop.service.UserService;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;

    private final CartService cartService;

    public LoginController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute UserDto userDto, HttpSession httpSession) {
        Optional<User> optionalUser = userService.authenticateUser(userDto.username(), userDto.password());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("cart", cartService.findCartForUser(user));
            return user.isAdmin() ? "redirect:admin" : "redirect:dashboard"; // Przekierowanie do strony po zalogowaniu
        } else {
            return "login";
        }
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute UserDto user, Model mode, RedirectAttributes redirectAttributes) {
        // Walidacja danych
        if (!userService.isUsernameAvailable(user.username())) {
            // Obsługa błędu - nazwa użytkownika jest już zajęta
            mode.addAttribute("usernameExists", true);
            return "/register"; // JSP dla formularza rejestracji z błędem
        }

        // Utworzenie nowego użytkownika
        userService.registerUser(user);
        redirectAttributes.addFlashAttribute("registrationSuccess", user.username());
        return "redirect:/login"; // Przekierowanie do strony logowania po rejestracji
    }

    @GetMapping("/login")
    public String login(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            return user.isAdmin() ? "redirect:admin" : "redirect:dashboard"; // Przekierowanie do strony po zalogowaniu
        } else {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";
    }
}