package shop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.dto.ProductDto;
import shop.entity.Product;
import shop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.productName());
        product.setPrice(productDto.price());
        return productRepository.save(product);
    }
}
