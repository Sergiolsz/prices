package com.app.prices.application.cqrs.query;

import java.time.LocalDateTime;

public record GetApplicablePriceQuery(Long brandId, Long productId, LocalDateTime applicationDate) {}
