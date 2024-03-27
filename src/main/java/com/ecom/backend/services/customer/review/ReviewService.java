package com.ecom.backend.services.customer.review;

import com.ecom.backend.dto.OrderedProductsResponseDto;
import com.ecom.backend.dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {
    OrderedProductsResponseDto getByOrderId(Long orderId);
    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
