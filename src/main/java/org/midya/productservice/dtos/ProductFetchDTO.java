package org.midya.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFetchDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;
}
