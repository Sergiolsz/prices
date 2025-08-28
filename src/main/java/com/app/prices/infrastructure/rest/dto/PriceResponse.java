package com.app.prices.infrastructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PriceResponse(
        @Schema(description = "Product ID", type = "integer", example = "35455") Integer productId,
        @Schema(description = "Brand ID", type = "integer", example = "1") Integer brandId,
        @Schema(description = "Price list ID", type = "integer", example = "2") Integer priceList,
        @Schema(description = "Start date of validity", type = "string", format = "date-time", example = "2025-08-27T00:00:00") String startDate,
        @Schema(description = "End date of validity", type = "string", format = "date-time", example = "2025-12-31T23:59:59") String endDate,
        @Schema(description = "Product price", type = "number", format = "double", example = "99.99") Double price,
        @Schema(description = "Currency of the price", type = "string", example = "EUR") String currency) {
}