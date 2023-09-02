package com.galeria.imagenes.galeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.galeria.imagenes.galeria.entity.Imagen;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

	List<Imagen> findByOrderById();
	
}
