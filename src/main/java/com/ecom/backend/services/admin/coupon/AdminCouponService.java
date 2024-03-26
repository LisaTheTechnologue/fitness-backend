package com.ecom.backend.services.admin.coupon;

import com.ecom.backend.entity.Coupon;

import java.util.List;

public interface AdminCouponService {
    Coupon create(Coupon coupon);
    List<Coupon> getAll();
}
