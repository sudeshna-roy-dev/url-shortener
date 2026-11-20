package com.urlshortner.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUrlRequest {

    @NotBlank(message = "Original URL cannot be empty")
    @Size(max = 2048, message = "URL is too long")
    @Pattern(
        regexp = "^https?://.+",
        message = "URL must start with http:// or https://"
    )
    private String originalUrl;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
