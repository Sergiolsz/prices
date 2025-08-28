package com.app.prices.application.cqrs.query;

import com.app.prices.domain.model.Price;

public interface GetApplicablePriceHandler {

    Price getApplicablePrice(GetApplicablePriceQuery query);
}