package com.uguinformatica.bluemoon_adminweb.service.product;

import com.uguinformatica.bluemoon_adminweb.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<List<Product>> getAllProducts(String token);
    Optional<Product> getProductByID(long id, String token);
    void updateProduct(Product product, String token);
    void createProduct(Product product, String token);
    void deleteProduct(long id, String token);
}
