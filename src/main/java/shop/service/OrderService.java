package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.ItemNotFound;
import shop.dto.OrderDto;
import shop.entity.Address;
import shop.entity.Cart;
import shop.entity.Order;
import shop.entity.User;
import shop.repository.AddressRepository;
import shop.repository.CartRepository;
import shop.repository.OrderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;


    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order createOrder(OrderDto orderDto, User user, Address address, Cart cart) {
        Order order = new Order();
        Address orderAddress = saveAddress(orderDto, address, user);
        order.setAddress(orderAddress);
        cart.setActive(false);

        order.setCart(saveCart(cart));
        return orderRepository.save(order);
    }

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    private Address saveAddress(OrderDto orderDto, Address address, User user) {

//        Optional<Address> byId = address != null ? addressRepository.findById(address.getId()) : Optional.empty();
//        Address address = byId.orElseGet(Address::new);
        address = address != null ? address : new Address();
        Address addressToSave = Boolean.parseBoolean(orderDto.updateAddress()) ? address : new Address();
        addressToSave.setUser(user);
        addressToSave.setAddressName(orderDto.addressName());
        addressToSave.setAddress(orderDto.address());
        addressToSave.setCountry(orderDto.country());
        addressToSave.setCity(orderDto.city());
        addressToSave.setPostalCode(orderDto.postalCode().replace("-", ""));
        return addressToSave.compareFields(address) ? address : addressRepository.save(addressToSave);
    }

    public Page<Order> getAllProducts(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order findById(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        return byId.orElseThrow(() -> new ItemNotFound("Order", id));
    }
}
