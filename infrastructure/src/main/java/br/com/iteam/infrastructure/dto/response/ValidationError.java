package br.com.iteam.infrastructure.dto.response;

public record ValidationError(String field, String message) {}