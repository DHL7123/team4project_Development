package com.evo.evoproject.controller.product.dto;

import com.evo.evoproject.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminRetrieveProductResponse {
    private List<Product> products;
    private String sort;
    private Integer soldout;
    private int currentPage;
    private int totalPages;
}

