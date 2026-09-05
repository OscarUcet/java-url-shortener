package com.oscaruceta.urlshortener.repository;

import com.oscaruceta.urlshortener.entity.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Long> {
}
