package com.oscaruceta.urlshortener.controller;

import com.oscaruceta.urlshortener.service.LinkService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class RedirectController {

    private final LinkService linkService;

    public RedirectController(LinkService linkService) {
        this.linkService = linkService;
    }

    @Operation(summary = "Redirect to the original URL", description = "Redirects the client to the original URL associated with the short code.")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Redirecting to the original URL"),
            @ApiResponse(responseCode = "404", description = "No short link exists for the supplied code")
    })
    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(
            @Parameter(description = "The short code to resolve", example = "aB3xYz", required = true) @PathVariable String code) {
        String originalUrl = linkService.getOriginalUrl(code);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
