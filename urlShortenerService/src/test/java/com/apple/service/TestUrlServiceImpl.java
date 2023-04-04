package com.apple.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.apple.binding.UrlDto;
import com.apple.entity.UrlEntity;
import com.apple.repo.UrlRepository;
import com.apple.utils.UrlUtils;

@ExtendWith(MockitoExtension.class)
public class TestUrlServiceImpl {

	@Mock
	private UrlRepository repository;

	@Mock
	private UrlUtils urlUtils;

	@InjectMocks
	private UrlServiceImpl serviceImpl;

	@Test
	public void TestGenerateShortUrlNotExits() {
		UrlDto dto = new UrlDto("https://www.google.com");

		UrlEntity urlEntity = new UrlEntity("https://www.google.com", "https://localhost:9000/1");

		when(urlUtils.generateShortUrl(urlEntity.getOriginalUrl())).thenReturn("049123b3");

		String actual = serviceImpl.generateShortUrl(dto);
		String expected = "049123b3";
		assertEquals(expected, actual);

	}

	@Test
	public void TestGenerateShortUrlExits() {
		UrlDto dto = new UrlDto("https://www.google.com");

		UrlEntity urlEntity = new UrlEntity("https://www.google.com", "https://localhost:9000/1");
		when(repository.findByOriginalUrl(dto.getUrl())).thenReturn(Optional.of(urlEntity));

		String actual = serviceImpl.generateShortUrl(dto);
		String expected = "https://localhost:9000/1";
		assertEquals(expected, actual);
	}

}
