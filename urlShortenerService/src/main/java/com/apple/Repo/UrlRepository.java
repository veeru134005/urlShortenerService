package com.apple.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apple.entity.UrlEntity;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
	public Optional<UrlEntity> findByShortenerUrl(String shortLink);

	public Optional<UrlEntity> findByOriginalUrl(String url);
}
