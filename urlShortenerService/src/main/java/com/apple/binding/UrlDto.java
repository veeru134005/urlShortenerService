package com.apple.binding;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("url")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UrlDto(String url) {
		super();
		this.url = url;
	}

	public UrlDto() {
		super();
	}

	@Override
	public String toString() {
		return "UrlDto [url=" + url + "]";
	}

}
