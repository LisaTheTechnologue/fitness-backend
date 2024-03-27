package com.ecom.backend.repository;

import com.ecom.backend.entity.Category;
import com.ecom.backend.entity.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends JpaRepository<FAQ,Long> {
}
