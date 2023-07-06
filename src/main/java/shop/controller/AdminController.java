package shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.dto.ProductDto;
import shop.entity.Order;
import shop.entity.Product;
import shop.service.OrderService;
import shop.service.ProductService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {


    private final ProductService productService;
    private final OrderService orderService;


    @GetMapping("/products")
    public String showProductList(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = productService.getAllProducts(pageRequest);
        model.addAttribute("products", products);
        return "admin/products"; // JSP dla listy produktów w panelu administratora
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "admin/addProduct";
    }

    @PostMapping("/addProduct")
    public String createProduct(@ModelAttribute ProductDto productDto) {
        productService.saveProduct(productDto);
        return "admin";
    }

    @GetMapping("/orders")
    public String showOrders(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page, @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Order> orders = orderService.getAllProducts(pageRequest);
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
    @GetMapping("/orders/{id}")
    public String showOrder(@PathVariable("id") Long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "admin/order";
    }
}

