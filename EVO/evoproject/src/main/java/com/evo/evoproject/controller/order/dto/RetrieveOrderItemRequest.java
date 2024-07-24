package com.evo.evoproject.controller.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveOrderItemRequest {
    private int productNo;
    private int quantity;
    private int price;
    private int shipping;
    private String productName;
    private String mainImage;
}