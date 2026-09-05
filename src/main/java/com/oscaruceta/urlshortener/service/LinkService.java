package com.oscaruceta.urlshortener.service;

import com.oscaruceta.urlshortener.entity.ShortLink;
import com.oscaruceta.urlshortener.repository.ShortLinkRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LinkService {

    private final ShortLinkRepository shortLinkRepository;

    public LinkService(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    public String createLink(String url) {
        String code = UUID.randomUUID().toString().substring(0, 6);

        ShortLink shortLink = new ShortLink();
        shortLink.setCode(code);
        shortLink.setOriginalUrl(url);

        shortLinkRepository.save(shortLink);

        return code;
    }
}
