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
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services.DocumentService;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services.dto.DocumentDTO;

@RestController
@RequestMapping(value = "/documents")
public class DocumentResource {

	
	@Autowired
	private DocumentService documentService;

	@GetMapping
	public ResponseEntity<List<Document>> findAll() {
		List<Document> list = documentService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Document> findById(@PathVariable Long id) {
		Document obj = documentService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody DocumentDTO documentDTO , 
			@RequestParam("token") String token) {		
		Document document = documentService.insert(documentDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(document.getId()).toUri();
		return ResponseEntity.created(uri).body("Documento " + document.getTipoDocumento() 
		+ " incluido para o usuário " + document.getClient().getNome() );
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		documentService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Document> update(@PathVariable Long id, @RequestBody Document obj) {
		obj = documentService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
