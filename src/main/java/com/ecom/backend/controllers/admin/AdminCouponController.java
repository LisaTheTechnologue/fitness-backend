package com.ecom.backend.controllers.admin;

import com.ecom.backend.entity.Coupon;
import com.ecom.backend.exceptions.ValidationException;
import com.ecom.backend.services.admin.coupon.AdminCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class AdminCouponController {

    private final AdminCouponService adminCouponService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody Coupon coupon){
        try {
            Coupon createdCoupon = adminCouponService.create(coupon);
            return ResponseEntity.ok(createdCoupon);
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<Coupon>> getAll () {
        return ResponseEntity.ok(adminCouponService.getAll());
    }
 }
