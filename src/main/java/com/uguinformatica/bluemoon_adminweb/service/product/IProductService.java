package com.uguinformatica.bluemoon_adminweb.service.product;

import com.uguinformatica.bluemoon_adminweb.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<List<Product>> getAllProducts();
    Optional<Product> getProductByID(long id);
    void updateProduct(Product product);
    void createProduct(Product product);
    void deleteProduct(long id);
}
