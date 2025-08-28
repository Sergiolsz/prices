package com.app.prices.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends GeneralException {

    public PriceNotFoundException(Long brandId, Long productId, LocalDateTime applicationDate) {
        super("No applicable price found for brand %d, product %d at %s"
                .formatted(brandId, productId, applicationDate));
    }
}
