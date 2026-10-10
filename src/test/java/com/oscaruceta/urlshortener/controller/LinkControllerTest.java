package com.oscaruceta.urlshortener.controller;

// import com.oscaruceta.urlshortener.controller.LinkController;
import com.oscaruceta.urlshortener.service.LinkService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(LinkController.class)
class LinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LinkService linkService;

    @Test
    void createLinkWithValidUrlReturnsOk() throws Exception {
        when(linkService.createLink("https://example.com"))
                .thenReturn("abc123");

        mockMvc.perform(post("/api/links")
                .contentType(APPLICATION_JSON)
                .content("""
                        {"url":"https://example.com"}
                        """))
                .andExpect(status().isOk())
                .andExpect(content().string("abc123"));

        verify(linkService).createLink("https://example.com");
    }

    @Test
    void createLinkWithInvalidUrlReturnsBadRequest() throws Exception {
        doThrow(new IllegalArgumentException("URL format is invalid"))
                .when(linkService)
                .createLink("not-a-url");

        mockMvc.perform(post("/api/links")
                .contentType(APPLICATION_JSON)
                .content("""
                        {"url":"not-a-url"}
                        """))
                .andExpect(status().isBadRequest());

        verify(linkService).createLink("not-a-url");
    }

    @Test
    void createLinkWithBlankUrlReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/links")
                .contentType(APPLICATION_JSON)
                .content("""
                        {"url":"   "}
                        """))
                .andExpect(status().isBadRequest());

        verify(linkService, never()).createLink(anyString());
    }
}
