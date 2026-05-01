package com.smartlink.smartlink.controller;

import com.smartlink.smartlink.model.ShortUrl;
import com.smartlink.smartlink.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortUrl> shorten(@RequestBody String originalUrl) {
        ShortUrl result = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        ShortUrl shortUrl = urlService.getByShortCode(shortCode);
        return ResponseEntity.status(302)
                .header("Location", shortUrl.getOriginalUrl())
                .build();
    }
 @GetMapping("/stats/{shortCode}")
public ResponseEntity<ShortUrl> getStats(@PathVariable String shortCode) {
    ShortUrl shortUrl = urlService.getStats(shortCode);
    return ResponseEntity.ok(shortUrl);
}
}