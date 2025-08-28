package com.app.prices.application.service;

import com.app.prices.application.cqrs.query.GetApplicablePriceHandler;
import com.app.prices.application.cqrs.query.GetApplicablePriceQuery;
import com.app.prices.domain.exception.PriceNotFoundException;
import com.app.prices.domain.model.Price;
import com.app.prices.domain.repository.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PriceQueryService implements GetApplicablePriceHandler {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price getApplicablePrice(GetApplicablePriceQuery query) {
        log.debug("Fetching price for brand={}, product={}, date={}",
                query.brandId(), query.productId(), query.applicationDate());

        return priceRepositoryPort.findProductPrice(query.brandId(), query.productId(),
                        query.applicationDate())
                .orElseThrow(() -> new PriceNotFoundException(
                        query.brandId(), query.productId(), query.applicationDate()));
    }
}