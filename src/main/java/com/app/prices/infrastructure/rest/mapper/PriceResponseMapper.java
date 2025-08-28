package com.app.prices.infrastructure.rest.mapper;

import com.app.prices.domain.model.Price;
import com.app.prices.infrastructure.config.MapStructConfig;
import com.app.prices.infrastructure.rest.dto.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PriceResponseMapper {

    PriceResponse priceToPriceResponse(Price price);
}
