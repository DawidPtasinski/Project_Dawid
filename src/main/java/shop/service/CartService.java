package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shop.dto.CartAddItemDto;
import shop.dto.CartItemUpdateQuantityDto;
import shop.entity.Cart;
import shop.entity.CartItem;
import shop.entity.Product;
import shop.entity.User;
import shop.repository.CartRepository;
import shop.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    public void addToCart(Cart cart, CartAddItemDto cartAddItemDto) {
        productRepository.findById(cartAddItemDto.productId()).ifPresent((product) -> addItem(cart,cartAddItemDto.productId(), cartAddItemDto.quantity()) );
    }

    public Cart findCartForUser(User user) {
        return cartRepository.findByUserIdAndActive(user.getId(), true).orElseGet(() -> new Cart(user));
    }

    public void addItem(Cart cart, Long productId, Integer quantity) {
        Set<CartItem> cartItems = cart.getCartItems();
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product with Id = "+ productId + " not exists!"));
        CartItem cartItem = cartItems.stream().filter(item -> item.getProduct().getId().equals(product.getId()))
                .findAny().orElseGet(() -> new CartItem(cart, product));
        cartItem.changeQuantity(quantity);
        cartItems.add(cartItem);
        cartRepository.save(cart);
    }

    public void updateCartItemQuantity(Cart cart, CartItemUpdateQuantityDto cartItemUpdateQuantity) {
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItems.stream().filter(item -> cartItemUpdateQuantity.productId().equals(item.getProduct().getId())).findAny().get();
        cartItem.setQuantity(cartItemUpdateQuantity.quantity());
        cartItem.setLastModificationDate(LocalDateTime.now());
        cartRepository.save(cart);
    }
}

