package com.projeto_integrador_gen.egide.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto_integrador_gen.egide.model.Usuario;
import com.projeto_integrador_gen.egide.repository.UsuarioRepository;
import com.projeto_integrador_gen.egide.services.UsuarioServices;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioServices services;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/perfil")
	public ResponseEntity<Object> retornaPerfil(@RequestParam(defaultValue = "") String nome)
	{
		Optional<Usuario> usuarioPerfil = services.visualizarPerfil(nome);
		if(usuarioPerfil.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioPerfil.get());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario inexistente!");
		}
	}
	
	@GetMapping("/nome/{nome}")
	   public ResponseEntity<List<Usuario>> getByNome(@PathVariable String nome)
 {
     return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
 }
	
	
	@PostMapping
	public ResponseEntity<Object> post (@Valid @RequestBody Usuario usuario)
	{
		Optional<Usuario> usuarioCriado = repository.findUsuarioByEmail(usuario.getEmail());
		if (usuarioCriado.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existe, tente outro!");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
		}
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put (@Valid @RequestBody Usuario usuario)
	{
		return ResponseEntity.ok(repository.save(usuario));				
	}
	
	@DeleteMapping("/{idUsuario}")
	public void delete(@PathVariable Long idUsuario)
	{
		repository.deleteById(idUsuario);
	}
	
	

}
