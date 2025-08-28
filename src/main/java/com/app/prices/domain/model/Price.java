package com.app.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
        Long productId,
        Long brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        String currency,
        Integer priority) {

}