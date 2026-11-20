package com.urlshortner.url_shortener.service;

import com.urlshortner.url_shortener.entity.ShortUrl;
import com.urlshortner.url_shortener.exception.ShortUrlInactiveException;
import com.urlshortner.url_shortener.exception.ShortUrlNotFoundException;
import com.urlshortner.url_shortener.repository.ShortUrlRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {

    private final ShortUrlRepository repository;
    private final ShortCodeGenerator codeGenerator;

    public ShortUrlService(
            ShortUrlRepository repository,
            ShortCodeGenerator codeGenerator) {
        this.repository = repository;
        this.codeGenerator = codeGenerator;
    }

    public ShortUrl createShortUrl(String originalUrl) {

        for (int attempt = 0; attempt < 5; attempt++) {

            String shortCode = codeGenerator.generateCode();

            if (repository.existsByShortCode(shortCode)) {
                continue;
            }

            ShortUrl shortUrl = new ShortUrl();
            shortUrl.setOriginalUrl(originalUrl);
            shortUrl.setShortCode(shortCode);

            try {
                return repository.save(shortUrl);
            } catch (DataIntegrityViolationException exception) {
                // Another request may have inserted the same code.
                // Retry with a newly generated code.
            }
        }

        throw new IllegalStateException(
                "Unable to generate a unique short code. Please try again."
        );
    }

    public String getOriginalUrl(String shortCode) {

        ShortUrl shortUrl = repository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ShortUrlNotFoundException("Short URL not found"));

        if (!shortUrl.isActive()) {
            throw new ShortUrlInactiveException("Short URL is inactive");
        }

        return shortUrl.getOriginalUrl();
    }
}
