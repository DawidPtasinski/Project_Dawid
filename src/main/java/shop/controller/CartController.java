package shop.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import shop.dto.CartAddItemDto;
import shop.dto.CartItemUpdateQuantityDto;
import shop.entity.Cart;
import shop.entity.CartItem;
import shop.entity.User;
import org.springframework.web.bind.annotation.*;
import shop.service.CartService;

import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public String addItem(@ModelAttribute CartAddItemDto cartAddItemDto, HttpSession httpSession) {
        Cart cart = (Cart) httpSession.getAttribute("cart");
        cartService.addToCart(cart, cartAddItemDto);
        return String.format("redirect:/products?page=%d&size=%d", cartAddItemDto.pageNumber(), cartAddItemDto.pageSize());
    }

    @PostMapping("/updateQuantity")
    public String updateQuantity(@ModelAttribute CartItemUpdateQuantityDto cartItemUpdateQuantity, HttpSession httpSession) {
        Cart cart = (Cart) httpSession.getAttribute("cart");
        cartService.updateCartItemQuantity(cart, cartItemUpdateQuantity);
        return "redirect:/cart";
    }

    @GetMapping("")
    public String getCart(HttpSession httpSession, Model model) {
        Cart cart = (Cart) httpSession.getAttribute("cart");
        if (cart != null) {
            model.addAttribute("sum", cart.sum());
            List<CartItem> list = cart.getCartItems().stream()
                    .sorted(Comparator.comparing(CartItem::getCreateDate)).toList();
            model.addAttribute("cartItems", list);
        }
        return "cart";

    }

}