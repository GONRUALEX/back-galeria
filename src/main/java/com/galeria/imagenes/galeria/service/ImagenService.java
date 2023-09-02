package com.galeria.imagenes.galeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galeria.imagenes.galeria.entity.Imagen;
import com.galeria.imagenes.galeria.repository.ImagenRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ImagenService {

	@Autowired
	ImagenRepository imagenRepository;
	
	public List<Imagen> list(){
		return imagenRepository.findByOrderById();
	}
	
	public void save(Imagen imagen) {
		imagenRepository.save(imagen);
	}
	
	public void delete(Long id) {
		imagenRepository.deleteById(id);
	}
	
	public Optional<Imagen> getOne(Long id){
		return imagenRepository.findById(id);
	}
	
	public boolean exists(Long id) {
		return imagenRepository.existsById(id);
	}
	
}
