package com.urlshortner.url_shortener.controller;

import com.urlshortner.url_shortener.dto.CreateUrlRequest;
import com.urlshortner.url_shortener.dto.CreateUrlResponse;
import com.urlshortner.url_shortener.entity.ShortUrl;
import com.urlshortner.url_shortener.service.ShortUrlService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/urls")
public class ShortUrlController {

    @Value("${app.base-url}")
    private String baseUrl;

    private final ShortUrlService shortUrlService;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUrlResponse createShortUrl(
            @Valid @RequestBody CreateUrlRequest request) {

        ShortUrl savedUrl = shortUrlService.createShortUrl(
                request.getOriginalUrl()
        );

        String shortUrl =
                baseUrl + "/" + savedUrl.getShortCode();

        return new CreateUrlResponse(
                savedUrl.getOriginalUrl(),
                savedUrl.getShortCode(),
                shortUrl,
                savedUrl.getCreatedAt()
        );
    }

}