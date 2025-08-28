package com.app.prices.domain.repository;


import com.app.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepositoryPort {

    Optional<Price> findProductPrice(Long brandId, Long productId,
            LocalDateTime applicationDate);
}