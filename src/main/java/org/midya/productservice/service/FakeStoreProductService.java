package org.midya.productservice.service;

import org.midya.productservice.dtos.ProductFetchDTO;
import org.midya.productservice.exceptions.ProductNotFoundException;
import org.midya.productservice.models.Category;
import org.midya.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final String url = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate = new RestTemplate();

    private Product convertToProduct(ProductFetchDTO productFetchDTO) {
        Product product = new Product();
        product.setId(productFetchDTO.getId());
        product.setTitle(productFetchDTO.getTitle());
        product.setDescription(productFetchDTO.getDescription());
        product.setPrice(productFetchDTO.getPrice());
        Category category = new Category();
        category.setName(productFetchDTO.getCategory());
        product.setCategory(category);
        product.setImageUrl(productFetchDTO.getImage());
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        System.out.println("Fetching all products from the API");
        ProductFetchDTO[] productFetchDTOS = restTemplate.getForObject(
                url,
                ProductFetchDTO[].class
        );
        assert productFetchDTOS != null;
        List<Product> allProducts = new ArrayList<>();
        for(ProductFetchDTO productFetchDTO : productFetchDTOS) {
            allProducts.add(convertToProduct(productFetchDTO));
        }
        return allProducts;
    }

    @Override
    public Product getProductById(Long id) {
        ProductFetchDTO productFetchDTO = restTemplate.getForObject(
                url + "/" + id,
                ProductFetchDTO.class
        );
        if(productFetchDTO == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }
        return convertToProduct(productFetchDTO);
    }

    @Override
    public List<Product> getProductsByLimit(int limit) {
        ProductFetchDTO[] productFetchDTOS = restTemplate.getForObject(
                url+"?limit="+limit,
                ProductFetchDTO[].class
        );
        assert productFetchDTOS != null;
        List<Product> limitedProducts = new ArrayList<>();
        for(ProductFetchDTO productFetchDTO : Arrays.copyOf(productFetchDTOS, limit)) {
            limitedProducts.add(convertToProduct(productFetchDTO));
        }
        return limitedProducts;
    }

    @Override
    public List<Product> getSortedProducts(String order) {
        ProductFetchDTO[] productFetchDTOS = restTemplate.getForObject(
                url+"?sort="+order,
                ProductFetchDTO[].class
        );
        assert productFetchDTOS != null;
        List<Product> sortedProducts = new ArrayList<>();
        for(ProductFetchDTO productFetchDTO : productFetchDTOS) {
            sortedProducts.add(convertToProduct(productFetchDTO));
        }
        return sortedProducts;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] categories = restTemplate.getForObject(
                url+ "/categories",
                String[].class
        );
        assert categories != null;
        List<Category> allCategories = new ArrayList<>();
        for(String category : categories) {
            Category categoryObject = new Category();
            categoryObject.setName(category);
            allCategories.add(categoryObject);
        }
        return allCategories;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        ProductFetchDTO[] productFetchDTOS = restTemplate.getForObject(
                url + "/category/" + categoryName,
                ProductFetchDTO[].class
        );
        assert productFetchDTOS != null;
        List<Product> productsByCategory = new ArrayList<>();
        for(ProductFetchDTO productFetchDTO : productFetchDTOS) {
            productsByCategory.add(convertToProduct(productFetchDTO));
        }
        return productsByCategory;
    }

    @Override
    public String deleteProduct(Long id) {
        restTemplate.delete(url + "/" + id);
        return "Product with id " + id + " deleted successfully!";
    }

    @Override
    public Product addProduct(Product product) {
        ProductFetchDTO productFetchDTO = new ProductFetchDTO();
        productFetchDTO.setTitle(product.getTitle());
        productFetchDTO.setDescription(product.getDescription());
        productFetchDTO.setPrice(product.getPrice());
        productFetchDTO.setCategory(product.getCategory().getName());
        productFetchDTO.setImage(product.getImageUrl());

        ProductFetchDTO response = restTemplate.postForObject(
                url,
                productFetchDTO,
                ProductFetchDTO.class
        );
        assert response != null;
        return convertToProduct(response);
    }

    @Override
    public void updateProduct(Product product) {
        ProductFetchDTO productFetchDTO = new ProductFetchDTO();
        productFetchDTO.setId(product.getId());
        productFetchDTO.setTitle(product.getTitle());
        productFetchDTO.setDescription(product.getDescription());
        productFetchDTO.setPrice(product.getPrice());
        productFetchDTO.setCategory(product.getCategory().getName());
        productFetchDTO.setImage(product.getImageUrl());

        restTemplate.put(
                url + "/" + product.getId(),
                productFetchDTO
        );
    }
}
