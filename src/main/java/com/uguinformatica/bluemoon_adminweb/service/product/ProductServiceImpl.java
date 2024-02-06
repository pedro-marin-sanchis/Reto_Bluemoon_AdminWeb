package com.uguinformatica.bluemoon_adminweb.service.product;
import com.uguinformatica.bluemoon_adminweb.model.Product;
import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService{

    private final RestTemplate restTemplate;

    @Autowired
    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<List<Product>> getAllProducts(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<List<Product>>() {};
        Optional<List<Product>> products = Optional.ofNullable(
                restTemplate.exchange(
                        APIConstants.API_URL + "/products",
                        HttpMethod.GET,
                        entity,
                        responseType
                ).getBody());

        if (products.isPresent()) {
            List<Product> filteredAndSortedProducts = products.get().stream()
                    .filter(product -> !product.getDisabled())
                    .sorted(Comparator.comparingLong(Product::getId))
                    .collect(Collectors.toList());
            return Optional.of(filteredAndSortedProducts);
        }
        return products;
    }


    @Override
    public Optional<Product> getProductByID(long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return Optional.ofNullable(
                restTemplate.exchange(
                        APIConstants.API_URL + "/products" + id,
                        HttpMethod.GET,
                        entity,
                        Product.class
                ).getBody());
    }

    @Override
    public void updateProduct(Product product, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);

        restTemplate.put(APIConstants.API_URL + "/products" + product.getId(), requestEntity);
    }

    @Override
    public void createProduct(Product product, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
        restTemplate.exchange(
                APIConstants.API_URL + "/products",
                HttpMethod.POST,
                requestEntity,
                Product.class
        );
    }

    @Override
    public void deleteProduct(long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                APIConstants.API_URL + "/products" + id,
                HttpMethod.DELETE,
                entity,
                Object.class);
    }

}
