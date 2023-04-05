package com.apple.controller;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apple.binding.UrlDto;
import com.apple.entity.UrlEntity;
import com.apple.service.UrlService;

@RestController
public class UrlShorteningController {

	@Autowired
	private UrlService urlService;

	@PostMapping(value = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> generateShortUrl(@RequestBody UrlDto urlDto) {
		if (urlDto == null || urlDto.getUrl().isEmpty())
			return new ResponseEntity<Map<String, String>>(Collections.singletonMap("response", "Please Enter Url"), HttpStatus.BAD_REQUEST);

		String uriString = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
		String shrUrl = uriString.concat("/api/").concat(urlService.generateShortUrl(urlDto));
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("response", shrUrl), HttpStatus.OK);
	}

	@GetMapping(value = "api/{shrtUrl}")
	public ResponseEntity<?> getOriginalUrl(@PathVariable String shrtUrl) {

		Optional<UrlEntity> urlEnty = urlService.getOriginalUrl(shrtUrl);

		if (urlEnty.isPresent())
			return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlEnty.get().getOriginalUrl())).build();

		return new ResponseEntity<String>(
				"<h2 style='color:red;text-align:center;margin-top:150px;font-size: xxx-large'>Invalid Url</h2>",
				HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/")
	public ModelAndView welcome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login.html");
		return modelAndView;
	}

}
