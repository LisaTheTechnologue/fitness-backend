package com.ecom.backend.entity;

import com.ecom.backend.dto.ProductDto;
import com.ecom.backend.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rating;
    @Lob
    private String description;
    @Lob
    private byte[] img;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public ReviewDto getDto() {
        ReviewDto dto = new ReviewDto();
        dto.setId(id);
        dto.setRating(rating);
        dto.setReturnedImg(img);
        dto.setDescription(description);
        dto.setProductId(product.getId());
        dto.setUserId(user.getId());
        dto.setUsername(user.getName());
        return dto;
    }
}

