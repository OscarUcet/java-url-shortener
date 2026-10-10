package com.oscaruceta.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateLinkRequest {

    @NotBlank(message = "URL cannot be blank")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
