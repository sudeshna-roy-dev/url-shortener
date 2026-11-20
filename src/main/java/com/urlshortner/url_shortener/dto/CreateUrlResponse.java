package com.urlshortner.url_shortener.dto;

import java.time.Instant;

public class CreateUrlResponse {

    private String originalUrl;
    private String shortCode;
    private String shortUrl;
    private Instant createdAt;

    public CreateUrlResponse(
            String originalUrl,
            String shortCode,
            String shortUrl,
            Instant createdAt) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.shortUrl = shortUrl;
        this.createdAt = createdAt;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
