package com.oscaruceta.urlshortener.service;

import com.oscaruceta.urlshortener.entity.ShortLink;
import com.oscaruceta.urlshortener.repository.ShortLinkRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Service
public class LinkService {

    private final ShortLinkRepository shortLinkRepository;

    public LinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    public String createLink(String url) {
        validateUrl(url);

        String code = UUID.randomUUID().toString().substring(0, 6);

        ShortLink shortLink = new ShortLink();
        shortLink.setCode(code);
        shortLink.setOriginalUrl(url);

        shortLinkRepository.save(shortLink);

        return code;
    }

    public String getOriginalUrl(String code) {
        ShortLink shortLink = shortLinkRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Short link not found"));

        return shortLink.getOriginalUrl();
    }

    private void validateUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL cannot be blank");
        }

        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme();

            if (scheme == null
                    || !(scheme.equalsIgnoreCase("http")
                    || scheme.equalsIgnoreCase("https"))
                    || uri.getHost() == null
                    || uri.getHost().isBlank()) {
                throw new IllegalArgumentException(
                        "URL must be an absolute HTTP or HTTPS URL with a valid host");
            }
        } catch (URISyntaxException exception) {
            throw new IllegalArgumentException("URL format is invalid", exception);
        }
    }
}
