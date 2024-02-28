package com.company.books.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.entity.Libro;
import com.company.books.backend.service.LibroService;

@RestController
@RequestMapping("api/libros")
@CrossOrigin(origins="http://localhost:5173/", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH,RequestMethod.DELETE})
public class LibroController {
	
	@Autowired
	LibroService service;
	
	@GetMapping
	public List<Libro> getAll(){
		return service.getListLibro();
	}	
	
	@GetMapping("{id}")
	public Libro getByIdLibro(@PathVariable Long id) {
		return service.getById(id).orElse(null);
	}
	
	@PostMapping
	public Libro saveLibro(@RequestBody Libro bean) {
		return service.saveLibro(bean);
	}
	
	public LibroController(LibroService libroServ) {
		this.service=libroServ;
	}
	
	@GetMapping("/buscarLibroPorNombre")
	public Set<Libro> buscarLibroPorNombre(@RequestParam("nombreDeLibro")String nombreDeLibro){
		return service.findPeliculasNombre(nombreDeLibro);
	}
	
	@GetMapping("/buscarLibroPorAutor")
	public Set<Libro> buscarLibroPorAutor(@RequestParam("nombreAutor")String nombreAutor){
		return service.findPeliculasNombre(nombreAutor);
	}
	
	@GetMapping("/buscarLibroPorPublicacionFecha")
	public Set<Libro> buscarLibroPorImagen(@RequestParam("publicacionFecha")String publicacionFecha){
		return service.findPeliculasNombre(publicacionFecha);
	}

}
