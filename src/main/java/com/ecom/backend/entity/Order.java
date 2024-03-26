package com.ecom.backend.entity;

import com.ecom.backend.dto.OrderDto;
import com.ecom.backend.dto.ProductDto;
import com.ecom.backend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String payment;

    private OrderStatus orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItems> cartItems;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public OrderDto getDto() {
        OrderDto dto = new OrderDto();
        dto.setId(id);
        dto.setAmount(amount);
        dto.setAddress(address);
        dto.setOrderDescription(orderDescription);
        dto.setDate(date);
        dto.setPayment(payment);
        dto.setOrderStatus(orderStatus);
        dto.setTrackingId(trackingId);
        dto.setUserName(user.getName());
        if(coupon !=null){
            dto.setCouponName(coupon.getName());
        }
        return dto;
    }
}
