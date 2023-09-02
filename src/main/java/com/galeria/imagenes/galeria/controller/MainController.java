package com.galeria.imagenes.galeria.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.galeria.imagenes.galeria.dto.Mensaje;
import com.galeria.imagenes.galeria.entity.Imagen;
import com.galeria.imagenes.galeria.service.CloudinaryService;
import com.galeria.imagenes.galeria.service.ImagenService;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin(origins="*")
public class MainController {

	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	ImagenService imagenService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Imagen>> list(){
		List<Imagen> list = imagenService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile) throws IOException{
		//comprobamos si es una imagen, si no retornamos
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		if (null==bi) {
			System.out.println("entra");
			return new ResponseEntity(new Mensaje("imagen no válida"), HttpStatus.BAD_REQUEST);
		}
		 Map result = cloudinaryService.upload(multipartFile);
		 Imagen imagen=
				 new Imagen((String)result.get("original_filename"),
						 (String) result.get("url"),
						 (String)result.get("public_id")
						 );
		 imagenService.save(imagen);
		 return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException{
		if(!imagenService.exists(id)) {
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		}
		Imagen imagen = imagenService.getOne(id).get();
		Map result = cloudinaryService.delete(imagen.getImagenId());
		imagenService.delete(id);
		return new ResponseEntity(new Mensaje("Imagen eliminada"), HttpStatus.OK);
	}
	
	
}
