package com.smartlink.smartlink.service;

import com.smartlink.smartlink.model.ShortUrl;
import com.smartlink.smartlink.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class UrlService {

    private final ShortUrlRepository repository;

    public UrlService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    public ShortUrl shortenUrl(String originalUrl) {
        String shortCode = generateCode();
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setOriginalUrl(originalUrl);
        shortUrl.setShortCode(shortCode);
        return repository.save(shortUrl);
    }

    public ShortUrl getByShortCode(String shortCode) {
        return repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Link bulunamadı"));
    }

    private String generateCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}