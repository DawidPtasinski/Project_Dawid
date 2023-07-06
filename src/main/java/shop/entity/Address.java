package shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@Entity
@Table
@Data
@EqualsAndHashCode
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String addressName;
    @Column
    private String country;
    @Column
    private String city;
    @Column
    @Size(max = 5)
    private String postalCode;

    @Column
    private String address;

    public String formattedPostalCode() {
        return postalCode.replaceAll("(^[0-9]{2})", "$1-");
    }

    public boolean compareFields(Address address) {
        if(address == null) {
            return false;
        }
        return this.addressName.equals(address.getAddressName()) &&
                this.country.equals(address.getCountry()) &&
                this.city.equals(address.getCity()) &&
                this.postalCode.equals(address.getPostalCode()) &&
                this.address.equals(address.getAddress());
    }

}
