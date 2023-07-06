package shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cart")
@Getter
@Setter
public class Cart {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    @Column
    private LocalDateTime createDate;

    @Column
    private Boolean active;

    public Cart() {
        createDate = LocalDateTime.now();
        active = true;
    }

    public Cart(User user) {
        this();
        this.user = user;
    }

    public BigDecimal sum() {
        return getCartItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
    }

    public Set<CartItem> getCartItems() {
        if(cartItems == null) {
            cartItems = new HashSet<>();
        }
        return cartItems;
    }
}