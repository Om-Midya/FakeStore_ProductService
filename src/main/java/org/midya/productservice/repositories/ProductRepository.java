package org.midya.productservice.repositories;

import org.midya.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long id);
    @Override
    Product save(Product product);
    @Override
    void deleteById(Long id);
    @Override
    List<Product> findAll();
}
