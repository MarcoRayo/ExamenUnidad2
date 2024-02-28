package com.company.books.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.books.backend.entity.Libro;
import com.company.books.backend.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository repo;
	
	public List<Libro> getListLibro(){
		return repo.findAll();
	}
	
	public Optional<Libro> getById(Long id){
		return repo.findById(id);
	}
	
	public Libro saveLibro(Libro bean) {
		return repo.save(bean);
	}
	
	public void deleteLibro (Long id) {
		repo.deleteById(id);		
	}
	
	public Set<Libro> findPeliculasNombre(String buscar){
		List<Libro> libroNombre = repo.findByNombreLibro(buscar);
		List<Libro> nombreAutor = repo.findByNombreAutor(buscar);
		List<Libro> fechaPublicacion = repo.findByFechaPublicacion(buscar);
		Set<Libro> respuesta = new HashSet<>();
		respuesta.addAll(libroNombre);		
		respuesta.addAll(nombreAutor);
		respuesta.addAll(fechaPublicacion);
		return respuesta;
	}
		
	
}
