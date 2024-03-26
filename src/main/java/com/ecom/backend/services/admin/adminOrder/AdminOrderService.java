package com.ecom.backend.services.admin.adminOrder;

import com.ecom.backend.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAll();
}
