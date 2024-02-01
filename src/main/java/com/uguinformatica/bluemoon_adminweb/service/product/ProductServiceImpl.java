package com.uguinformatica.bluemoon_adminweb.service.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uguinformatica.bluemoon_adminweb.model.Product;
import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
    public Optional<List<Product>> getAllProducts() {
        ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<List<Product>>() {};
        Optional<List<Product>> products = Optional.ofNullable(restTemplate.exchange(APIConstants.API_URL + "/products/", HttpMethod.GET, null, responseType).getBody());
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
    public Optional<Product> getProductByID(long id) {
        return Optional.ofNullable(restTemplate.getForEntity(APIConstants.API_URL + "/products/" + id, Product.class).getBody());
    }

    @Override
    public void updateProduct(Product product) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonRequest = objectMapper.writeValueAsString(product);
            System.out.println("JSON Request: " + jsonRequest);

            HttpEntity<Product> requestEntity = new HttpEntity<>(product);
            restTemplate.put(APIConstants.API_URL + "/products/" + product.getId(), requestEntity);
        } catch (JsonProcessingException e) {
            // Handle exception
            e.printStackTrace();
        }
    }

    @Override
    public void createProduct(Product product) {
        HttpEntity<Product> requestEntity = new HttpEntity<>(product);
        restTemplate.exchange(
                APIConstants.API_URL + "/products/",
                HttpMethod.POST,
                requestEntity,
                Product.class
        );
    }

    @Override
    public void deleteProduct(long id) {
        restTemplate.delete(APIConstants.API_URL + "/products/" + id);
    }

}
