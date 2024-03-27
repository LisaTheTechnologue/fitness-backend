package com.ecom.backend.controllers.customer;

import com.ecom.backend.dto.OrderDto;
import com.ecom.backend.dto.OrderedProductsResponseDto;
import com.ecom.backend.dto.ReviewDto;
import com.ecom.backend.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDto> getByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(reviewService.getByOrderId(orderId));
    }
    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        ReviewDto dto = reviewService.giveReview(reviewDto);
        if (dto == null)
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
