package com.ecom.backend.services.admin.faq;

import com.ecom.backend.dto.FAQDto;

public interface FAQService {
    FAQDto create(Long productId, FAQDto faqDto);
}
