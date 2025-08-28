package com.app.prices.infrastructure.rest;

import com.app.prices.application.cqrs.query.GetApplicablePriceHandler;
import com.app.prices.application.cqrs.query.GetApplicablePriceQuery;
import com.app.prices.infrastructure.rest.dto.PriceResponse;
import com.app.prices.infrastructure.rest.mapper.PriceResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/prices")
@Validated
public class PriceController {

    private final GetApplicablePriceHandler getApplicablePriceHandler;
    private final PriceResponseMapper priceResponseMapper;

    @Operation(
            summary = "Get applicable product price",
            description = "Returns the applicable price for a product and brand at a given date."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Applicable price found",
                    content = @Content(schema = @Schema(implementation = PriceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No applicable price found",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<PriceResponse> getApplicablePrice(
            @Parameter(
                    description = "Product ID",
                    example = "35455"
            )
            @RequestParam @NotNull Long productId,

            @Parameter(
                    description = "Brand ID",
                    example = "1"
            )
            @RequestParam @NotNull Long brandId,

            @Parameter(
                    description = "Application date : format (yyyy-MM-dd'T'HH:mm:ss)",
                    example = "2020-06-14T16:00:00"
            )
            @RequestParam @NotNull
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime applicationDate
    ) {
        log.info("Received request: brandId={}, productId={}, applicationDate={}",
                brandId, productId, applicationDate);

        var price = getApplicablePriceHandler.getApplicablePrice(
                new GetApplicablePriceQuery(brandId, productId, applicationDate));

        if (price == null) {
            log.warn("No applicable price found for brandId={}, productId={}, applicationDate={}",
                    brandId, productId, applicationDate);
            return ResponseEntity.notFound().build();
        }

        PriceResponse priceResponse = priceResponseMapper.priceToPriceResponse(price);

        log.info("Returning price response: {}", priceResponse);
        return ResponseEntity.ok(priceResponse);
    }
}
