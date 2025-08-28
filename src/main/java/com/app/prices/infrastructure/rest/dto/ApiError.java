package com.app.prices.infrastructure.rest.dto;

public record ApiError(String error, String message, int status) {}
