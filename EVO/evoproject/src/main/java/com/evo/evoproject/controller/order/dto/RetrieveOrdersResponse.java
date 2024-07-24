package com.evo.evoproject.controller.order.dto;

import com.evo.evoproject.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetrieveOrdersResponse {
    private List<Order> orders;
    private int userNo;
    private int currentPage;
    private int totalPages;
    private int size;
}
