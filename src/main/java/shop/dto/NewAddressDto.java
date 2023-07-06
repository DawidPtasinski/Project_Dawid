package shop.dto;

public record NewAddressDto(String addressName,
                            String country,
                            String city,
                            String postalCode,
                            String address
) {
}
