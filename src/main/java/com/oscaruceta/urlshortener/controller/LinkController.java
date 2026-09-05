package com.oscaruceta.urlshortener.controller;

import com.oscaruceta.urlshortener.dto.CreateLinkRequest;
import com.oscaruceta.urlshortener.service.LinkService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    // Dependency Injection (DI).
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public String createLink(@RequestBody CreateLinkRequest request) {
        return linkService.createLink(request.getUrl());
    }

    @GetMapping("/{code}")
    public String getOriginalUrl(@PathVariable String code) {
        return linkService.getOriginalUrl(code);
    }
}
