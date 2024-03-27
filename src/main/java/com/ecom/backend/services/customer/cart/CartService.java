package com.ecom.backend.services.customer.cart;

import com.ecom.backend.dto.AddProductInCartDto;
import com.ecom.backend.dto.OrderDto;
import com.ecom.backend.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId (Long userId);
    OrderDto applyCoupon(Long userId, String code);
    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto placeOrder(PlaceOrderDto placeOrderDto);
    List<OrderDto> getMyPlacedOrder (Long userId);
}
