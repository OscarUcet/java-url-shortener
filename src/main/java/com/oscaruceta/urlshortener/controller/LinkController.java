package com.oscaruceta.urlshortener.controller;

import com.oscaruceta.urlshortener.dto.CreateLinkRequest;
import com.oscaruceta.urlshortener.service.LinkService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final LinkService linkService;

    // Dependency Injection (DI).
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @Operation(summary = "Create a shortened link", description = "Creates a short code for a valid HTTP or HTTPS URL.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Short link created successfully", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "aB3xYz"))),
            @ApiResponse(responseCode = "400", description = "The request body or URL is invalid")
    })
    @PostMapping
    public String createLink(@RequestBody @Valid CreateLinkRequest request) {
        return linkService.createLink(request.getUrl());
    }

    @Operation(summary = "Retrieve the original URL", description = "Returns the original URL associated with a short code.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Original URL retrieved successfully", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class), examples = @ExampleObject(value = "https://www.youtube.com/"))),
            @ApiResponse(responseCode = "404", description = "No short link exists for the supplied code")
    })
    @GetMapping("/{code}")
    public String getOriginalUrl(
            @Parameter(description = "The short code to look up", example = "aB3xYz", required = true) @PathVariable String code) {
        return linkService.getOriginalUrl(code);
    }

}
