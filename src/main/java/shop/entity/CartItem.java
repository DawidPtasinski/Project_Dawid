package shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItem {

    @EmbeddedId
    private CartItemKey id;

    @JoinColumn(name = "cart_id")
    @MapsId("cartId")
    @ManyToOne
    private Cart cart;

    @JoinColumn(name = "product_id")
    @MapsId("productId")
    @ManyToOne
    private Product product;

    @Column
    private Integer quantity = 0;

    @Column
    private LocalDateTime createDate = LocalDateTime.now();

    @Column
    private LocalDateTime lastModificationDate = LocalDateTime.now();

    public CartItem() {
    }

    public CartItem(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
        this.id = new CartItemKey();
        id.setCartId(cart.getId());
        id.setProductId(product.getId());
    }

    public void changeQuantity(int quantity) {
        this.quantity += quantity;
        if(this.quantity < 0) {
            this.quantity = 0;
        }
        lastModificationDate = LocalDateTime.now();
    }

    public BigDecimal sum() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Embeddable
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class CartItemKey implements Serializable {
        @Column(name = "cartId")
        Long cartId;
        @Column(name = "productId")
        Long productId;

    }

}
