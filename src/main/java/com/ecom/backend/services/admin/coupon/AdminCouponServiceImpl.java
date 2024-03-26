package com.ecom.backend.services.admin.coupon;

import com.ecom.backend.entity.Coupon;
import com.ecom.backend.exceptions.ValidationException;
import com.ecom.backend.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {

    private final CouponRepository couponRepository;

    @Override
    public Coupon create(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists.");
        }
        return couponRepository.save(coupon);
    }
    @Override
    public List<Coupon> getAll(){
        return couponRepository.findAll();
    }

}
