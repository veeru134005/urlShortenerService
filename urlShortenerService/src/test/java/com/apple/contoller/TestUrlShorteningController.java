package com.apple.contoller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.apple.binding.UrlDto;
import com.apple.controller.UrlShorteningController;
import com.apple.entity.UrlEntity;
import com.apple.service.UrlService;

@ExtendWith(MockitoExtension.class)
public class TestUrlShorteningController {

	@Mock
	private UrlService urlService;

	@InjectMocks
	private UrlShorteningController shorteningController;

	@Test
	public void TestGenerateShortUrl() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		UrlDto dto = new UrlDto("https://www.google.com");

		when(urlService.generateShortUrl(dto)).thenReturn("https://localhost:9000/1");

		ResponseEntity<?> generateShortUrl = shorteningController.generateShortUrl(dto);

		assertEquals(HttpStatus.OK, generateShortUrl.getStatusCode());
	}

	@Test
	public void TestgetOriginalUrl() throws Exception {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		UrlEntity urlEntity = new UrlEntity("https://www.google.com", "https://localhost:9000/1");
		when(urlService.getOriginalUrl("1")).thenReturn(Optional.of(urlEntity));

		ResponseEntity<?> originalUrl = shorteningController.getOriginalUrl("1");

		assertEquals(HttpStatus.FOUND, originalUrl.getStatusCode());
	}

}
