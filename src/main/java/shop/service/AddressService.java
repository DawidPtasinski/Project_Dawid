package shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.dto.NewAddressDto;
import shop.entity.Address;
import shop.entity.User;
import shop.repository.AddressRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;


    public Address saveAddress(NewAddressDto newAddressDto, User user) {
        Address address = new Address();
        address.setAddressName(newAddressDto.addressName());
        address.setCity(newAddressDto.city());
        address.setCountry(newAddressDto.country());
        address.setPostalCode(newAddressDto.postalCode());
        address.setAddress(newAddressDto.address());
        address.setUser(user);
        return addressRepository.save(address);
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Address with id=%d not exist", id)));
    }

    public List<Address> findAllByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }
}
