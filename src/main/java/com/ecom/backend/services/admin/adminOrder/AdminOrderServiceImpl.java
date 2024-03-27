package com.ecom.backend.services.admin.adminOrder;

import com.ecom.backend.dto.OrderDto;
import com.ecom.backend.entity.CartItems;
import com.ecom.backend.entity.Order;
import com.ecom.backend.entity.Product;
import com.ecom.backend.enums.OrderStatus;
import com.ecom.backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService{

    private final OrderRepository orderRepository;

    public List<OrderDto> getAll(){
        List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped, OrderStatus.Delivered));
        return orderList.stream().map(Order::getDto).collect(Collectors.toList());
    }

    public OrderDto changeOrderStatus(Long orderId, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent() ) {
            Order order = optionalOrder.get();

            if (Objects.equals(status, "Shipped")) {
                order.setOrderStatus(OrderStatus.Shipped);
            } else if (Objects.equals(status, "Delivered")) {
                order.setOrderStatus(OrderStatus.Delivered);
            }
            return orderRepository.save(order).getDto();
        }
        return null;
    }
}
