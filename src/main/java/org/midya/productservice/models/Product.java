package org.midya.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private double price;
    @ManyToOne
    @JoinColumn
    private Category category;
    private String imageUrl;
}
