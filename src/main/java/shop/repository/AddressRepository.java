package shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUserId(Long userId);
}
