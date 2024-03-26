package com.ecom.backend.services.admin.adminOrder;

import com.ecom.backend.dto.OrderDto;
import com.ecom.backend.entity.Order;
import com.ecom.backend.enums.OrderStatus;
import com.ecom.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService{

    private final OrderRepository orderRepository;

    public List<OrderDto> getAll(){
        List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped, OrderStatus.Delivered));
        return orderList.stream().map(Order::getDto).collect(Collectors.toList());

    }
}
