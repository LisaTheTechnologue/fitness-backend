package com.ecom.backend.services.admin.faq;

import com.ecom.backend.dto.FAQDto;
import com.ecom.backend.entity.Coupon;
import com.ecom.backend.entity.FAQ;
import com.ecom.backend.entity.Product;
import com.ecom.backend.exceptions.ValidationException;
import com.ecom.backend.repository.CouponRepository;
import com.ecom.backend.repository.FAQRepository;
import com.ecom.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;
    private final ProductRepository productRepository;

    @Override
    public FAQDto create(Long productId, FAQDto faqDto){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            FAQ faq = new FAQ();

            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());
            return faqRepository.save(faq).getDto();
        }
        return null;
    }
}
