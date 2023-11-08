package com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.entities.Document;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.entities.User;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services.DocumentService;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Exemplo API")
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private DocumentService documentService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
    @ApiOperation("Incluir usuário")
	public ResponseEntity<User> insert(@RequestBody User user, 
			@RequestParam("token") String token) {
		user = userService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = userService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
