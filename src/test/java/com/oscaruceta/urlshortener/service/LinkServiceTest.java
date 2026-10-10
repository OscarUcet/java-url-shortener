package com.oscaruceta.urlshortener.service;

import com.oscaruceta.urlshortener.repository.ShortLinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class LinkServiceTest {

    private LinkService linkService;

    @BeforeEach
    void setUp() {
        ShortLinkRepository repository = mock(ShortLinkRepository.class);
        linkService = new LinkService(repository);
    }

    @Test
    void createLinkAcceptsHttpsUrl() {
        assertDoesNotThrow(
                () -> linkService.createLink("https://example.com"));
    }

    @Test
    void createLinkAcceptsHttpUrl() {
        assertDoesNotThrow(
                () -> linkService.createLink("http://example.com"));
    }

    @Test
    void createLinkRejectsInvalidUrl() {
        assertThrows(
                IllegalArgumentException.class,
                () -> linkService.createLink("not-a-url"));
    }

    @Test
    void createLinkRejectsUnsupportedScheme() {
        assertThrows(
                IllegalArgumentException.class,
                () -> linkService.createLink("ftp://example.com"));
    }

    @Test
    void createLinkRejectsUrlWithoutHost() {
        assertThrows(
                IllegalArgumentException.class,
                () -> linkService.createLink("https:///"));
    }
}
