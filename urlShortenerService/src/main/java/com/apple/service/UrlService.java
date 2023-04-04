package com.apple.service;

import java.util.Optional;

import com.apple.binding.UrlDto;
import com.apple.entity.UrlEntity;

public interface UrlService {

	public String generateShortUrl(UrlDto urlDto);

	public Optional<UrlEntity> getOriginalUrl(String shrtUrl);
}
