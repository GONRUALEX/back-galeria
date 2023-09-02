package com.galeria.imagenes.galeria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Imagen {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String imagenUrl;
	private String imagenId;
	
	public Imagen() {}
	
	public Imagen(Long id, String name, String imagenUrl, String imagenId) {
		super();
		this.id = id;
		this.name = name;
		this.imagenUrl = imagenUrl;
		this.imagenId = imagenId;
	}
	
	public Imagen( String name, String imagenUrl, String imagenId) {
		super();
		this.name = name;
		this.imagenUrl = imagenUrl;
		this.imagenId = imagenId;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagenUrl() {
		return imagenUrl;
	}
	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}
	public String getImagenId() {
		return imagenId;
	}
	public void setImagenId(String imagenId) {
		this.imagenId = imagenId;
	}
	
	
	
}
