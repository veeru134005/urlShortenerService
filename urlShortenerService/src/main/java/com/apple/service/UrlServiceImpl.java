package com.apple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.binding.UrlDto;
import com.apple.entity.UrlEntity;
import com.apple.repo.UrlRepository;
import com.apple.utils.UrlUtils;

@Service
public class UrlServiceImpl implements UrlService {

	@Autowired
	private UrlRepository repository;

	@Autowired
	private UrlUtils urlUtils;

	@Override
	public String generateShortUrl(UrlDto urlDto) {

		Optional<UrlEntity> urlAlrdyExist = repository.findByOriginalUrl(urlDto.getUrl());

		if (urlAlrdyExist.isPresent()) {
			return urlAlrdyExist.get().getShortenerUrl();
		}
		String shrtUrl = urlUtils.generateShortUrl(urlDto.getUrl());
		UrlEntity urlEntity = new UrlEntity(urlDto.getUrl(), shrtUrl);
		repository.save(urlEntity);

		return shrtUrl;
	}

	@Override
	public Optional<UrlEntity> getOriginalUrl(String shrtUrl) {

		return repository.findByShortenerUrl(shrtUrl);

	}

}
