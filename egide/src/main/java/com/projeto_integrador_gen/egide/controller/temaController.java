package com.projeto_integrador_gen.egide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_integrador_gen.egide.model.Tema;
import com.projeto_integrador_gen.egide.repository.temaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin("*")
public class temaController {
	
	@Autowired
	private temaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	@GetMapping("/{idTema}")
	public ResponseEntity<Tema> getById(@PathVariable Long idTema)
	{
		return repository.findById(idTema).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema)
	{
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@RequestBody Tema tema)
	{
		return ResponseEntity.ok(repository.save(tema));
	}
	
	@DeleteMapping("/{idTema}")
	public void delete (@PathVariable Long idTema)
	{
		repository.deleteById(idTema);
	}
	
	
	
	

}
