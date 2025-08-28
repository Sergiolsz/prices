package com.app.prices.infrastructure.database.adapter;

import com.app.prices.domain.model.Price;
import com.app.prices.domain.repository.PriceRepositoryPort;
import com.app.prices.infrastructure.database.mapper.PriceEntityMapper;
import com.app.prices.infrastructure.database.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Override
    public Optional<Price> findProductPrice(Long brandId, Long productId,
            LocalDateTime applicationDate) {

        return priceRepository.findFirstApplicablePrice(productId, brandId, applicationDate)
                .map(priceEntityMapper::entityToPrice);
    }

}
