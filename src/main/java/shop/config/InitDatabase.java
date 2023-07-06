package shop.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import shop.dto.NewAddressDto;
import shop.dto.ProductDto;
import shop.dto.UserDto;
import shop.entity.Product;
import shop.entity.Role;
import shop.entity.User;
import shop.service.AddressService;
import shop.service.ProductService;
import shop.service.UserService;

import java.math.BigDecimal;
import java.util.Random;
import java.util.Set;

@Component
@AllArgsConstructor
public class InitDatabase {

    private final UserService userService;
    private final ProductService productService;
    private final AddressService addressService;


    @PostConstruct
    public void init() {
      // initUsers();
       //initProducts();
    }

    private void initUsers() {
        UserDto userDto = new UserDto("test", "test");
        User user = userService.registerUser(userDto);

        userDto = new UserDto("admin", "admin");
        userService.registerUserWithRoles(userDto, Set.of(Role.ADMIN));

        NewAddressDto newAddressDto = new NewAddressDto("My Address", "Poland", "Warsaw", "12345", "Ulica 12/12");


        addressService.saveAddress(newAddressDto, user);
    }


    public void initProducts() {
        Random random = new Random();
        random.nextDouble(100);
        String name = "Item_Name_";
        ProductDto product;
        for(int i = 0; i < 1000; i ++) {
            String productName = (name + i);
            BigDecimal price = BigDecimal.valueOf(random.nextDouble(100));
            product = new ProductDto(productName, price);
            productService.saveProduct(product);
            System.out.println("Product " + product + " saved!");
        }
    }
}
