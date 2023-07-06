package shop.dto;

public record CartAddItemDto(Long productId,
                             Integer quantity,
                             Integer pageNumber,
                             Integer pageSize) {
}
