package com.apple.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "url")
public class UrlEntity {

	public UrlEntity() {
		super();
	}

	public UrlEntity(String originalUrl, String shortenerUrl) {
		this.originalUrl = originalUrl;
		this.shortenerUrl = shortenerUrl;
		this.createdDate = LocalDate.now();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Lob
	@Column(name = "Origial_Url", nullable = false)
	private String originalUrl;

	@Column(name = "Shortener_Url", nullable = false)
	private String shortenerUrl;

	@Column(nullable = false)
	private LocalDate createdDate;

}
