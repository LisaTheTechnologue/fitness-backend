package com.ecom.backend.repository;

import com.ecom.backend.dto.OrderDto;
import com.ecom.backend.entity.Category;
import com.ecom.backend.entity.Order;
import com.ecom.backend.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
}
