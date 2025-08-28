package com.app.prices.infrastructure.database.mapper;

import com.app.prices.domain.model.Price;
import com.app.prices.infrastructure.config.MapStructConfig;
import com.app.prices.infrastructure.database.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapStructConfig.class)
public interface PriceEntityMapper {

    Price entityToPrice(PriceEntity entity);
}